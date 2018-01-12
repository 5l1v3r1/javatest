package com.orleven.javatest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Name: com.orleven.JavatestApp
 * @Author: orleven
 * @Date: 18-1-8
 * @Description:
 */
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableAsync
//@EnableScheduling
//@EnableAutoConfiguration(exclude = JmxAutoConfiguration.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class JavatestApp extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    public static Log log ;

    public static ApplicationContext ctx;

    public static void main( String[] args ) {
        JavatestApp.log = LogFactory.getLog(JavatestApp.class);
        JavatestApp.ctx = SpringApplication.run(JavatestApp.class, args);
        ControlCenter controlCenter = JavatestApp.ctx.getBean(ControlCenter.class);
//        controlCenter.init();
    }

    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8001);
    }
}
