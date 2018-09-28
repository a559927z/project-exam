package com.ks.config;

import com.ks.constants.UrlConstants;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

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
        /**
         * 403
         */
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
         *
         * @param request
         * @return
         */
        private boolean isAuth(HttpServletRequest request) {
            System.out.println("是否有认证权限");
//            EmpExtendDto empInfo = (EmpExtendDto) request.getSession().getAttribute("empInfo");
//            if (null == empInfo) {
//                return false;
//            }
            return true;
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
//            // admin 走shiro权限
//            if (requestURI.contains("/admin/") ||
//                    requestURI.contains("/assets/") ||
//                    requestURI.contains("/app/login/toLogin/") ||
//                    requestURI.contains("/app/login/doLogin/")
//                    ) {
//                filterChain.doFilter(srequest, sresponse);
//            }
//            // app 走是否激活验证
//            else if (1 != 1) {
//
//                filterChain.doFilter(srequest, sresponse);
//            } else {
//                return;
//            }
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
