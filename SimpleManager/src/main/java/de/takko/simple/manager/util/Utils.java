package de.takko.simple.manager.util;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class Utils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void saveFile(File file, FileConfiguration configuration) {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}