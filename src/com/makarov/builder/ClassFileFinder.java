package com.makarov.builder;

import java.io.File;
import java.net.URL;

public class ClassFileFinder {

    public File findClassFile(Class<?> clazz) {
        String path = getClassFilePath(clazz);
        System.out.println(clazz.getProtectionDomain().getCodeSource());
        return new File(path);
    }

    public File[] findClassFiles(Class<?>... classes) {
        File[] files = new File[classes.length];
        for (int i = 0; i < classes.length; i++) {
            files[i] = findClassFile(classes[i]);
        }

        return files;
    }

    //"\"C:\\Program Files\\Java\\jdk1.8.0_60\\jre\\..\\bin\\jar.exe\" -cvfm Agent.jar com\\makarov\\builder\\manifest.mf com\\makarov\\builder\\Main.class com\\makarov\\builder\\JarBuilder.class"

    private String getClassFilePath(Class<?> clazz) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(getNormalClassFileName(clazz));
        if (resource != null) {
            return resource.getPath();
        }

        return null;
    }

    private String getNormalClassFileName(Class<?> clazz) {
        String name = clazz.getName();
        return name.replace(".", "/") + ".class";
    }
}
