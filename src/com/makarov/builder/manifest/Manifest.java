package com.makarov.builder.manifest;

import java.util.HashMap;
import java.util.Map;


public class Manifest {

    private String path;

    private String fileName;

    private Map<String, String> parameters = new HashMap<>();

    public Manifest(String fileName) {
        this.fileName = fileName;
    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public void create() {

    }
}
