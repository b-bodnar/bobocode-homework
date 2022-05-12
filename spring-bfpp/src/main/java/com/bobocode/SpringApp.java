package com.bobocode;

import com.bobocode.config.RootConfig;
import com.bobocode.model.OldClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApp {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(RootConfig.class);
        var oldClass =context.getBean(OldClass.class);
        System.out.println(oldClass);
    }
}
