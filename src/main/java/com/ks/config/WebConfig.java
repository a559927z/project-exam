package com.ks.config;

import com.alibaba.fastjson.JSON;
import com.ks.constants.CookieConstants;
import com.ks.constants.UrlConstants;
import com.ks.constants.UserInfoConstants;
import com.ks.dao.ExamUserInfoMapper;
import com.ks.dao.PublicUserInfoMapper;
import com.ks.dto.ExamUserInfo;
import com.ks.dto.ExamUserInfoExample;
import com.ks.dto.PublicUserInfo;
import com.ks.dto.PublicUserInfoExample;
import com.ks.utils.CookieUtils;
import com.ks.utils.cache.LoadingCacheUtil;
import com.ks.vo.VisitorVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.RemoteIpFilter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private PublicUserInfoMapper publicUserInfoMapper;

    @Autowired
    private ExamUserInfoMapper examUserInfoMapper;

    /**
     * 配置静态访问资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/assets/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthHandlerInterceptor());
        super.addInterceptors(registry);
    }

    public class AuthHandlerInterceptor extends HandlerInterceptorAdapter {

        final String unAuthorized = UrlConstants.URI_TO_LOGIN;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 通过
            if (isAuth(request)) {
                // 走拦截器链
                return true;
            }
            // 不通过重定向到403
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            response.sendRedirect(basePath + unAuthorized);
            // 不走拦截器链
            return false;
        }

        /**
         * 是否有认证权限
         * 1：从客户端的cookie里得到enName   (客户端的cookie是用户落地成功会返回给客户端， 生存期3个月)
         * 2：通过本地缓存取检验是否存在（本地缓存里在登录时查出 已激活的账号才保存在缓存里， 生存期2天）
         *
         * @param request
         * @return
         */
        private boolean isAuth(HttpServletRequest request) {
            String ctxPath = "";
            String active = env.getProperty("spring.profiles.active");
            if ("dev".equalsIgnoreCase(active)) {
                ctxPath = "/exam";
            }

            String requestURI = request.getRequestURI();
            if (StringUtils.startsWith(requestURI, ctxPath + "/app/login/")
                    || StringUtils.startsWith(requestURI, ctxPath + "/admin/")) {
                log.info("通过：都不做过认证");
                return true;
            }

            String enName = "";
            String userInfoJSON = CookieUtils.getCookieValue(request, CookieConstants.USER_INFO_KEY);
            if (StringUtils.isNotBlank(userInfoJSON)) {
                VisitorVo visitor = JSON.parseObject(userInfoJSON, VisitorVo.class);
                enName = visitor.getEnName();
            }

            if (StringUtils.isBlank(enName)) {
                log.info("不通过：cookie没值");
                return false;
            }
            String userInfo = null;
            try {
                userInfo = LoadingCacheUtil.getInstance().get(enName, String.class);
                if (null == userInfo) {
                    return saveCache(enName);
                } else {
                    log.info("通过：缓存有");
                    // TODO 缓存里的值是否合法 select db
                    return true;
                }
            } catch (ExecutionException e) {
                return saveCache(enName);
            }
        }

        /**
         * @param enName
         * @return
         */
        private boolean saveCache(String enName) {
            ExamUserInfoExample example = new ExamUserInfoExample();
            example.createCriteria().andAccountEqualTo(enName);
            try {
                List<ExamUserInfo> exists = examUserInfoMapper.selectByExample(example);
                if (CollectionUtils.isNotEmpty(exists)) {
                    LoadingCacheUtil.getInstance().save(CookieConstants.USER_INFO_OBJ + enName, JSON.toJSONString(exists.get(0)));
                    log.info("通过：缓存没有，从数据库里带出，正常缓存是在/app/login/toLogin里就设置的，防止客户端2天没有退出，缓存没了。");
                    return true;
                } else {
                    log.info("不通过：缓存没有，从数据库里没带出。");
                    return false;
                }
            } catch (Exception e) {
                log.error("不通过：缓存没有，从数据库里查询出错");
                return false;
            }
        }
    }


    /**
     * 自定义filter
     */
    public class MyFilter implements Filter {
        @Override
        public void destroy() {
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            srequest.setCharacterEncoding("UTF-8");
            HttpServletRequest request = (HttpServletRequest) srequest;
            request.setCharacterEncoding("UTF-8");
            String requestURI = request.getRequestURI();

            System.out.println("this is MyFilter,url :" + requestURI);
            filterChain.doFilter(srequest, sresponse);
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
        }
    }

    /**
     * Spring Boot2.0以上版本EmbeddedServletContainerCustomizer被WebServerFactoryCustomizer替代
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {

                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

                container.addErrorPages(error401Page, error404Page, error500Page);
            }
        };
    }
}
