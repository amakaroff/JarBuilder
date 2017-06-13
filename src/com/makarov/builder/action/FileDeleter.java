package com.makarov.builder.action;

import java.io.File;

public class FileDeleter {

    public static void deleteAfterCreateFile(String pathDeletableFile, String pathCreatableFile) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isDeleted = false;
                while (!isDeleted) {
                    File creatableFile = new File(pathCreatableFile);
                    if (creatableFile.isFile()) {
                        File deletableFile = new File(pathDeletableFile);
                        isDeleted = deletableFile.delete();
                    }
                }
            }
        });

        thread.start();
    }
}
