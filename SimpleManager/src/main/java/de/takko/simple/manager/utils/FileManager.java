package de.takko.simple.manager.utils;

import de.takko.simple.manager.SimpleModule;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileManager {

    private File file;
    private FileConfiguration cfg;
    private SimpleModule module;
    private String prefixKey;
    private String adminPermission;
    private HashMap<String, Object> defaults = new HashMap<>();

    public FileManager(SimpleModule module, String prefixKey, String adminPermission) {
        this.module = module;
        new File(module.getModuleDir(module)).mkdirs();
        file = new File(module.getModuleDir(module), "config.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        this.prefixKey = prefixKey;
        this.adminPermission = adminPermission;

        createFiles();
    }

    private void createFiles() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                new Logger().log(Logger.LogType.ERROR, e.getMessage());
            }
        }
    }
    public String get(String key) {
        return ChatColor.translateAlternateColorCodes('&', cfg.getString(key));
    }
    public String getWithPrefix(String key) {
        return ChatColor.translateAlternateColorCodes('&', (cfg.getString(prefixKey) + cfg.getString(key)));
    }
    public void set(String key, Object value) {
        cfg.set(key, value);
        save();
    }
    public void addDefault(String key, Object value) {
        defaults.put(key, value);
    }
    public void saveDefaults() {
        for (String key : defaults.keySet()) {
            cfg.set(key, defaults.get(key));
        }
        defaults.clear();
        save();
    }
    public boolean hasPermission(Player player, String permission) {
        if (player.hasPermission(permission) || player.hasPermission(adminPermission))
            return true;
        return false;
    }
    public String getModulePath() {
        return module.getModuleDir(module);
    }
    public File getFile() {
        return file;
    }
    public FileConfiguration getConfiguration() {
        return cfg;
    }
    public String getAdminPermission() {
        return adminPermission;
    }
    public String getPrefixKey() {
        return prefixKey;
    }
    private void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}