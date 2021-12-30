package de.takko.simple.module.backpack;

import de.takko.simple.manager.base.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;

public class Util {

    public void saveInventory(Player player, Inventory inventory) {
        File file = new File(BackpackModule.getFileManager().getModulePath(), "inventory.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set(player.getUniqueId().toString(), inventory.getContents());
        Utils.saveFile(file, configuration);
    }

    @SuppressWarnings("unchecked")
    public Inventory getSavedInventoryFromFile(Player player) {
        File file = new File(BackpackModule.getFileManager().getModulePath(), "inventory.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        Inventory inventory = Bukkit.createInventory(player, 3*9, "Test");
        ItemStack[] content = ((List<ItemStack>) configuration.get(player.getUniqueId().toString())).toArray(new ItemStack[0]);
        inventory.setContents(content);
        Utils.saveFile(file, configuration);
        return inventory;
    }
}