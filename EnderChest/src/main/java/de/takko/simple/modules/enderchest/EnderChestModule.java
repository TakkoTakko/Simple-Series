package de.takko.simple.modules.enderchest;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.utils.FileManager;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class EnderChestModule extends SimpleModule {

    public EnderChestModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.enderchest.admin");

        initConfig();

        PluginCommand command = registerCommand("enderchest", "ec");
        command.setExecutor(new EnderChestCommand());
    }

    @Override
    public void terminate() {

    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bEnderchest&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");
        fileManager.addDefault("OfflinePlayer", "&cDieser Spieler ist offline");
        fileManager.addDefault("permission", "simple.enderchest.use");
        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}