package com.ks;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * (一、二)    入门篇 web综合开发
 * http://www.ityouknow.com/springboot/2016/02/03/spring-boot-web.html
 * <p>
 * (三)  Spring boot中Redis的使用
 * http://www.ityouknow.com/springboot/2016/03/06/spring-boot-redis.html
 * <p>
 * (四)  thymeleaf使用详解
 * http://www.ityouknow.com/springboot/2016/05/01/spring-boot-thymeleaf.html
 * <p>
 * (五)  spring data jpa的使用
 * http://www.ityouknow.com/springboot/2016/08/20/spring-boo-jpa.html
 * <p>
 * (六)  如何优雅的使用mybatis
 * http://www.ityouknow.com/springboot/2016/11/06/spring-boo-mybatis.html
 * <p>
 * (七)  springboot+mybatis多数据源最简解决方案
 * http://www.ityouknow.com/springboot/2016/11/25/spring-boot-multi-mybatis.html
 * <p>
 * (八)  RabbitMQ详解
 * http://www.ityouknow.com/springboot/2016/11/30/spring-boot-rabbitMQ.html
 * <p>
 * (九)  定时任务
 * http://www.ityouknow.com/springboot/2016/12/02/spring-boot-scheduler.html
 * <p>
 * (十)  邮件服务
 * http://www.ityouknow.com/springboot/2017/05/06/springboot-mail.html
 * <p>
 * <p>
 * <p>
 *     https://www.cnblogs.com/1315925303zxz/p/7364552.html
 *     https://blog.csdn.net/xiaolyuh123/article/details/73506189
 * Title: ${type_name} <br/>
 * <p>
 * Description: <br/>
 *
 * @author jxzhang
 * @DATE 2018年08月27日 16:29
 * @Verdion 1.0 版本
 * ${tags}
 */
@MapperScan("com.ks.mapper")
@SpringBootApplication
@EnableScheduling
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
