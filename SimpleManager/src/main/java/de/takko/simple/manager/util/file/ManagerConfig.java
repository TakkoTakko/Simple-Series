package de.takko.simple.manager.util.file;

import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.util.Utils;
import org.bukkit.command.CommandSender;
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

        setDefaultObject("Prefix", "&7[&bSimpleManager&7] &r");
        setDefaultObject("NoPermission", "&cDazu hast du keine Rechte!");

        setDefaultObject("placeholder.separator", "&7, &e");
        setDefaultObject("permission", "simple.manager.use");

        setDefaultObject("module.all.show.size", "&aEs wurden &e%module_size% &aModule geladen.");
        setDefaultObject("module.all.show.format", "&aName&7: &e%module_name% &7| &aVersion&7: &e%module_version% &7| &aAutoren&7: &e%module_authors%");
        setDefaultObject("module.all.names", "&aAlle Module&7: &e%module_all_names%");
        setDefaultObject("module.all.commands.no", "&cEs wurden keine Befehle gefunden.");
        setDefaultObject("module.all.commands.format", "&aAlle Befehle&7: &e%module_commands%");

        setDefaultObject("module.specific.show.all", "&aName&7: &e%module_name% &7| &aVersion&7: &e%module_version% &7| &aAutoren&7: &e%module_authors% &7| &aBeschreibung&7: &e%module_description% &7| &aBefehle&7: &e%module_commands%");
        setDefaultObject("module.specific.show.name", "&aName&7: &e%module_name% ");
        setDefaultObject("module.specific.show.version", "&aVersion&7: &e%module_version%");
        setDefaultObject("module.specific.show.authors", "&aAutoren&7: &e%module_authors%");
        setDefaultObject("module.specific.show.description", "&aBeschreibung&7: &e%module_description%");
        setDefaultObject("module.specific.show.commands", "&aBefehle&7: &e%module_commands%");

        setDefaultObject("module.main_prefix", "&bYourServer.de");
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
     * get Prefix
     * @return
     */
    public String getPrefix() {
        return Utils.translateColorCodes(configuration.getString("Prefix"));
    }
    /**
     * get String with translated color coes
     * @param key
     * @return
     */
    public String getStringWithTranslatedColorCodes(String key) {
        return Utils.translateColorCodes(configuration.getString(key));
    }
    /**
     * get String with Prefix
     * @param key
     * @return
     */
    public String getStringWithPrefix(String key) {
        return Utils.translateColorCodes(configuration.getString("Prefix") + configuration.getString(key));
    }
    /**
     * get string
     * @param key
     * @return
     */
    public String getString(String key) {
        return Utils.translateColorCodes(configuration.getString(key));
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

    /**
     * get File
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     * get Configuration
     * @return
     */
    public FileConfiguration getConfiguration() {
        return configuration;
    }
}