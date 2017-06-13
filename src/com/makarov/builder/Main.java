package com.makarov.builder;

import com.makarov.builder.manifest.Manifest;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        JarBuilder builder = new JarBuilder();
        builder.build(new Class[]{Main.class, Manifest.class});
    }
}
