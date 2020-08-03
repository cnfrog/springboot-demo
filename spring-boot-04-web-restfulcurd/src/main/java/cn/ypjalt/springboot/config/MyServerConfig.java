package cn.ypjalt.springboot.config;

import cn.ypjalt.springboot.fliter.Myfilter;
import cn.ypjalt.springboot.listener.MyListener;
import cn.ypjalt.springboot.servlet.MyServlet;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServerConfig {

    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean registrationBean =
                new ServletRegistrationBean(new MyServlet(), "/myServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;

    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new Myfilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener() {
        ServletListenerRegistrationBean<MyListener> myListenerServletRegistrationBean = new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return myListenerServletRegistrationBean;
    }


    @Bean
    public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
        // 定制嵌入式的 servlet 容器相关的规则
        TomcatServletWebServerFactory factory =
                new TomcatServletWebServerFactory();
        factory.setPort(8090);
        return factory;
    }
}

