package com.bobocode.bfpp;

import com.bobocode.annotation.NewRealization;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyDeprecatedBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        var beanDefinitions = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitions) {
            var beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            var className = beanDefinition.getBeanClassName();
            System.err.println(className);
            try {
                Class<?> beanClass = Class.forName(className);
                var deprecatedAnnotation = beanClass.getAnnotation(NewRealization.class);
                if (beanClass.isAnnotationPresent(NewRealization.class)) {
                    beanDefinition.setBeanClassName(deprecatedAnnotation.newClass().getName());

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchBeanDefinitionException e){
                System.out.println(e.getBeanName());
                System.out.println(e.getBeanType());
            }
        }
    }
}
