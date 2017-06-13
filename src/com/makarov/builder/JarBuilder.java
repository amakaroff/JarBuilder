package com.makarov.builder;

import java.io.File;
import java.io.IOException;

public class JarBuilder {

    private final String jar = "\"" + System.getProperty("java.home") + "\\..\\bin\\jar.exe" + "\"";

    private String protectionDomainPath;

    private final String jarCommand = "-cvfm";

    private final String cdCommand = "/cd /d";

    private final String jarName = "Agent.jar";

    public void build(Class<?>[] classes) {
        if (protectionDomainPath == null && classes != null && classes.length > 0) {
            protectionDomainPath = classes[0].getProtectionDomain().getCodeSource().getLocation().getPath().substring(1).replace("/", "\\");
        }
        build(classes, createManifest());
    }

    public void build(Class<?>[] classes, File manifest) {
        Runtime runtime = getRuntime();

        System.out.println(jar + " " + commandBuilder(classes, manifest));
        System.out.println(cdCommand + " "  + protectionDomainPath);
        try {
            //runtime.exec(cdCommand + " " + protectionDomainPath);
            runtime.exec("cmd " + cdCommand + " " + protectionDomainPath + " && " + jar + " " + commandBuilder(classes, manifest));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(1);
        }
    }

    private Runtime getRuntime() {
        return Runtime.getRuntime();
    }

    private String commandBuilder(Class<?>[] classes, File manifest) {
        StringBuilder builder = new StringBuilder();
        builder.append(jarCommand).append(" ")
                .append(jarName).append(" ")
                .append(manifest.getAbsolutePath()).append(" ");

        for (Class<?> clazz : classes) {
            builder.append(getNormalClassFileName(clazz)).append(" ");
        }
        builder.delete(builder.length() - 1, builder.length());

        return builder.toString();
    }

    private File createManifest() {
        return new File("D:\\Java\\jarBuiler\\src\\com\\makarov\\builder\\manifest.mf");
    }

    private String getNormalClassFileName(Class<?> clazz) {
        String name = clazz.getName();
        return name.replace(".", "\\") + ".class";
    }
}
