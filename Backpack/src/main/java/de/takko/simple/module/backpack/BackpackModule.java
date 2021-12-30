package de.takko.simple.module.backpack;

import de.takko.simple.manager.base.ModuleInfo;
import de.takko.simple.manager.base.SimpleManager;
import de.takko.simple.manager.base.SimpleModule;
import de.takko.simple.manager.base.util.file.FileManager;
import org.bukkit.Server;

public class BackpackModule extends SimpleModule {

    public BackpackModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.backpack.admin");

        initConfig();

    }

    @Override
    public void terminate() {

    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bBackpack&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("permission", "simple.backpack.use");
        fileManager.addDefault("NoPlayer", "&cDazu musst du ein Spieler sein!");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}
