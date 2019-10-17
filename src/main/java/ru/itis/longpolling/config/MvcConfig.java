package ru.itis.longpolling.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/chat").setViewName("chat");
        registry.addViewController("/signin").setViewName("login");
        registry.addViewController("/signup").setViewName("register");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
