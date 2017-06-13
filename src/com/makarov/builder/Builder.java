package com.makarov.builder;

import com.makarov.builder.manifest.Manifest;

/**
 * Created by alma0317 on 13.06.2017.
 */
public interface Builder {

    Builder addJarName(String name);

    Builder addClass(Class<?> clazz);

    Builder addAgentClass(Class<?> clazz);

    Builder addManifest(Manifest manifest);

    String build();
}
