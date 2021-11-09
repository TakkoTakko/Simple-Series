package de.takko.simple.modules.enderchest;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.utils.FileManager;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class EnderchestModule extends SimpleModule {

    public EnderchestModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.enderchest.admin");

        initConfig();

        registerCommand("enderchest").setExecutor(new EnderchestCommand());
        registerCommand("ec").setExecutor(new EnderchestCommand());
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