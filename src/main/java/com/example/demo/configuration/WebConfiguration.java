package com.example.demo.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /** resource resolver **/
    /*
    * 1) mira que el resource exista en el fichero estatico
    * 2) sino existe devuelve el indice
    *
    * Se puede definir un endpoint en este caso es /app/**, lo que le sigue `**`
    * es el resource path
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/app/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath,
                                                   Resource location) throws IOException {

                        System.out.println(resourcePath);
                        Resource requestedResource = location.createRelative(resourcePath);
                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                                : new ClassPathResource("/static/index.html");
                    }
                });
    }

    /*
    //you can configure Handle interceptor to be applied to all incoming requests or to specifics URL
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authenticationInterceptor)
                .addPathPatterns("/**").order(Ordered.HIGHEST_PRECEDENCE)
                .excludePathPatterns(
                        "/database",
                        "/sexes",
                        "/rols-hc3",
                        "/documents",
                        "/especialitats",
                        "/",
                        "/app",
                        "/app/**",
                        "/getConfig/**",
                        "/error",
                        "/*.ico",
                        "/index.html",
                        "/*.js",
                        "/*.ttf",
                        "/*.woff2",
                        "/*.css");
        registry.addInterceptor(this.auditableInterceptor)
                .addPathPatterns("/**").order(Ordered.LOWEST_PRECEDENCE)
                .excludePathPatterns(
                        "/database",
                        "/sexes",
                        "/rols-hc3",
                        "/documents",
                        "/especialitats",
                        "/recursos-consultes-externes/{id}",
                        "/",
                        "/app",
                        "/app/**",
                        "/login",
                        "/auth/login",
                        "/getConfig/**",
                        "/error",
                        "/*.ico",
                        "/index.html",
                        "/*.js",
                        "/*.ttf",
                        "/*.woff2",
                        "/*.css");
    }


     */


    /** configuración para fuera del localhost o red local **/
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}
