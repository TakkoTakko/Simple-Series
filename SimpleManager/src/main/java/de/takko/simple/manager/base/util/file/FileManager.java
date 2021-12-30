package de.takko.simple.manager.base.util.file;

import de.takko.simple.manager.base.SimpleModule;
import de.takko.simple.manager.base.util.Logger;
import de.takko.simple.manager.base.util.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
        this.module.getDataFolder().mkdirs();
        file = new File(this.module.getDataFolder(), "config.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        this.prefixKey = prefixKey;
        this.adminPermission = adminPermission;

        createFile();
    }

    public void createFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                new Logger().log(Logger.LogType.ERROR, e.getMessage());
            }
        }
    }

    public String get(String key) {
        String get = Utils.translateColorCodes(cfg.getString(key));
        return get.contains("%manager_module_prefix%") ? get.replaceAll("%manager_module_prefix%", getWithoutReplacements("module.main_prefix")) : get;
    }

    public String getWithPrefix(String key) {
        String get = Utils.translateColorCodes(cfg.getString(key));
        return get.contains("%manager_module_prefix%") ? Utils.translateColorCodes(cfg.getString(prefixKey)) + get.replaceAll("%manager_module_prefix%", getWithoutReplacements("module.main_prefix")) : Utils.translateColorCodes(cfg.getString(prefixKey)) + get;
    }

    public String getWithout(String key) {
        String get = cfg.getString(key);
        return get.contains("%manager_module_prefix%") ? get.replaceAll("%manager_module_prefix%", getWithoutReplacements("module.main_prefix")) : get;
    }

    public String getWithoutReplacements(String key) {
        String get = cfg.getString(key);
        return get == null ? null : Utils.translateColorCodes(get);
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
            if (cfg.get(key) == null) {
                cfg.set(key, defaults.get(key));
            }
        }
        defaults.clear();
        save();
    }

    public boolean hasPermission(CommandSender sender, String permission) {
        return sender.hasPermission(permission) || sender.hasPermission(adminPermission);
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

    private void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}