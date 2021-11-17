package de.takko.simple.manager.util;

import de.takko.simple.manager.SimpleManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ManagerConfig {

    private final File file;
    private final FileConfiguration configuration;

    public ManagerConfig() {
        file = new File(SimpleManager.getPlugin(SimpleManager.class).getDataFolder(), "config.yml");
        configuration = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        load();
    }

    private void load() {
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        setDefaultObject("Prefix", "&7[&bwSimpleManager&7] &r");
        setDefaultObject("NoPermission", "&cDazu hast du keine Rechte!");

        setDefaultObject("placeholder.seperator", ", ");

        setDefaultObject("module.all.show.size", "&aEs wurden &e%module_size% &aModule geladen.");
        setDefaultObject("module.all.show.format", "&aName&7: &e%module_name% &7| &aVersion&7: &e%module_version% &7| &aAutoren&7: &e%module_authors%");

        setDefaultObject("module.specific.show.all", "&aName&7: &e%module_name% &7| &aVersion&7: &e%module_version% &7| &aAutoren&7: &e%module_authors% &7| &aBeschreibung&7: &e%module_description% &7| &aBefehle&7: &e%module_commands%");
        setDefaultObject("module.specific.show.name", "");
        setDefaultObject("module.specific.show.version", "");
        setDefaultObject("module.specific.show.authors", "");
        setDefaultObject("module.specific.show.description", "");
        setDefaultObject("module.specific.show.commands", "");
    }

    private void setDefaultObject(String key, Object value) {
        if (configuration.get(key) == null)
            configuration.set(key, value);
        save();
    }
    private void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get string
     * @param key
     * @return
     */
    public String getString(String key) {
        return configuration.getString(key);
    }
    /**
     * get integer
     * @param key
     * @return
     */
    public Integer getInt(String key) {
        return configuration.getInt(key);
    }
    /**
     * get double
     * @param key
     * @return
     */
    public Double getDouble(String key) {
        return configuration.getDouble(key);
    }
    /**
     * get object
     * @param key
     * @return
     */
    public Object get(String key) {
        return configuration.get(key);
    }
    /**
     * get boolean
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        return configuration.getBoolean(key);
    }
}