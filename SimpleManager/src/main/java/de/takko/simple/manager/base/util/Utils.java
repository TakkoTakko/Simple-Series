package de.takko.simple.manager.base.util;

import de.takko.simple.manager.base.SimpleModule;
import de.takko.simple.manager.base.util.file.ManagerConfig;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    public static String translateColorCodes(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String replaceModulePlaceholders(SimpleModule module, String string) {
        ManagerConfig managerConfig = new ManagerConfig();
        return string
                .replaceAll("%module_name%", module.getModuleInfo().getName())
                .replaceAll("%module_version%", module.getModuleInfo().getVersion())
                .replaceAll("%module_authors%", String.join(managerConfig.getString("placeholder.separator"), module.getModuleInfo().getAuthors()))
                .replaceAll("%module_description%", module.getModuleInfo().getDescription())
                .replaceAll("%module_commands%", String.join(managerConfig.getString("placeholder.separator"), module.getModuleInfo().getCommands()));
    }
}