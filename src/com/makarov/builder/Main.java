package com.makarov.builder;

import com.makarov.core.ConfigLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        JarBuilder builder = new JarBuilder();
        builder.build(new Class[]{Main.class});
    }
}
