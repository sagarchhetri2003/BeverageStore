package com.system.beverage_store.config;

import com.system.beverage_store.pojo.UserPojo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public UserPojo userPojo() {
        return new UserPojo();
    }
}
