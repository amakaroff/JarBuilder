package com.makarov.builder;

import com.makarov.builder.manifest.Manifest;

import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        AgentJarBuilder builder = new AgentJarBuilder();
        String agents = AgentJarBuilder.getJarBuilder().addAgentClass(Main.class).addClass(Manifest.class).addJarName("Agents").build();
        System.out.println(agents);
    }
}
