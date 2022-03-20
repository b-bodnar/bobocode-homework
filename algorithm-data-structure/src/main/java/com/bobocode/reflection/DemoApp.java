package com.bobocode.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DemoApp {
    @SneakyThrows
    public static void main(String[] args) {
        var randomizer = Class.forName("com.bobocode.reflection.Randomizer");
        var classLoader = randomizer.getClassLoader();
        var interfaceToImplement = new Class<?>[]{randomizer};
        InvocationHandler handler = (proxy, method, args1) -> {
            if (method.getName().equals("randomize")) {
                var elements = (List<?>) args1[0];
                var index = ThreadLocalRandom.current().nextInt(elements.size());
                return elements.get(index);
            }
            throw new UnsupportedOperationException();
        };

        var randomizeInstance = Proxy.newProxyInstance(classLoader, interfaceToImplement, handler);
        var randomizeMethod = randomizeInstance.getClass().getMethod("randomize", List.class);
        var res = randomizeMethod.invoke(randomizeInstance, List.of(1, 2, 3, 500));
        System.out.println(res);
    }
}
