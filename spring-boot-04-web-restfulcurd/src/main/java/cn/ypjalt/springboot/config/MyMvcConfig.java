package cn.ypjalt.springboot.config;

import cn.ypjalt.springboot.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 使用WebMvcConfigurer来扩展 SpringMVC 的功能
// 使用 EnableWebMvc 使得自动导入失效
//@EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送 good 请求也来到 success 页面
        registry.addViewController("/good").setViewName("success");

    }

    // 所有的WebMvcConfigurer组件会一起起作用
    @Bean//  将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");

            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 静态资源: *.css,*.js
//                registry.addInterceptor(new LoginHandlerInterceptor())
//                        .addPathPatterns("/**")
//                        .excludePathPatterns("/index.html", "/", "/user/login",
//                                "/webjars/**", "/asserts/**");
            }
        };
        return configurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }
}
