package com.makarov.builder;

/**
 * Created by Алексей on 13.06.2017.
 */
public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
