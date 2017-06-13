package com.makarov.builder;

import com.makarov.builder.manifest.Manifest;

public interface Builder {

    Builder addJarName(String name);

    Builder addClass(Class<?> clazz);

    Builder addAgentClass(Class<?> clazz);

    Builder addManifest(Manifest manifest);

    String build(boolean isDeleteManifest);

    String build();
}
