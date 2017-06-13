package com.makarov.builder.constant;

import java.io.File;

/**
 * Created by alma0317 on 13.06.2017.
 */
public class BuildConstants {

    public static final String JAR_COMMAND = "-cfvm";

    public static final String JAR = "\""
            + System.getProperty("java.home")
            + File.separator + ".."
            + File.separator + "bin"
            + File.separator + "jar.exe"
            + "\"";

    public static final String JAR_SUFFIX = ".jar";

    public static final String MANIFEST_SUFFIX = ".mf";

    public static final String CLASS_FILE_SUFFIX = ".class";
}
