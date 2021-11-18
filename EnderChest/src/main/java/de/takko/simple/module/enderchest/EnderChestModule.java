package de.takko.simple.module.enderchest;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.file.FileManager;
import org.bukkit.Server;

public class EnderChestModule extends SimpleModule {

    public EnderChestModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.enderchest.admin");

        initConfig();

        registerCommand("enderchest", "ec").setExecutor(new EnderChestCommand());
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