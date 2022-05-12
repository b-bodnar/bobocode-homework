package com.bobocode.config;

import com.bobocode.bfpp.MyDeprecatedBeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.bobocode")
public class RootConfig {

    @Bean
    public static BeanFactoryPostProcessor myDeprecatedBeanFactoryPostProcessor() {
        return new MyDeprecatedBeanFactoryPostProcessor();
    }
}
