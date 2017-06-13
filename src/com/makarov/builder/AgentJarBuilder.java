package com.makarov.builder;

import com.makarov.builder.action.FileDeleter;
import com.makarov.builder.constant.BuildConstants;
import com.makarov.builder.manifest.Manifest;

import java.io.File;
import java.io.IOException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

public class AgentJarBuilder {

    public static Builder getJarBuilder() {
        return new JarBuilder();
    }

    private static class JarBuilder implements Builder {

        private String commandPattern = "%s %s %s %s %s";

        private String jarName = "Agent.jar";

        private List<Class<?>> classes = new ArrayList<>();

        private Class<?> agentClass;

        private Manifest manifest;

        @Override
        public Builder addJarName(String name) {
            if (!name.contains(BuildConstants.JAR_SUFFIX)) {
                name = name + BuildConstants.JAR_SUFFIX;
            }

            this.jarName = name;
            return this;
        }

        @Override
        public Builder addClass(Class<?> clazz) {
            this.classes.add(clazz);
            return this;
        }

        @Override
        public Builder addManifest(Manifest manifest) {
            this.manifest = manifest;
            return this;
        }

        @Override
        public Builder addAgentClass(Class<?> clazz) {
            this.agentClass = clazz;
            addClass(this.agentClass);
            return this;
        }

        @Override
        public String build() {
            if (agentClass == null) {
                //TODO: try find agentClass from classes
                throw new RuntimeException("Agent class must be");
            }

            if (manifest == null) {
                this.manifest = Manifest.createDefaultAgentManifest(agentClass);
            }

            String manifestPath = manifest.create();
            String command = String.format(commandPattern, BuildConstants.JAR, BuildConstants.JAR_COMMAND, jarName, manifestPath, toClassCommand());
            System.out.println(command);
            try {
                Runtime.getRuntime().exec(command);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

            String jarPath = System.getProperty("user.dir") + File.separator + jarName;
            FileDeleter.deleteAfterCreateFile(manifestPath, jarPath);

            return jarPath;
        }

        private String toClassCommand() {
            StringBuilder builder = new StringBuilder();

            for (Class<?> clazz : classes) {
                ProtectionDomain domain = clazz.getProtectionDomain();
                if (domain != null) {
                    String classFileProtectionDomain = domain.getCodeSource().getLocation().getPath().substring(1).replace("/", File.separator);
                    String classFilePath = clazz.getName().replace(".", File.separator) + BuildConstants.CLASS_FILE_SUFFIX;

                    builder.append("-C ").append(classFileProtectionDomain).append(" ")
                            .append(classFilePath).append(" ");
                }
            }

            return builder.toString();
        }
    }
}