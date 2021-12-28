package de.takko.simple.module.broadcast;

import de.takko.simple.manager.base.ModuleInfo;
import de.takko.simple.manager.base.SimpleManager;
import de.takko.simple.manager.base.SimpleModule;
import de.takko.simple.manager.base.util.file.FileManager;
import org.bukkit.Server;

public class BroadcastModule extends SimpleModule {

    public BroadcastModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.broadcast.admin");

        initConfig();

        registerCommand("broadcast", "bc").setExecutor(new BroadcastCommand());
    }

    @Override
    public void terminate() {

    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bBroadcast&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("permission", "simple.broadcast.use");
        fileManager.addDefault("header_footer", true);
        fileManager.addDefault("syntax", "&cBitte benutze&7: &6/broadcast &7<&6message&7>");
        fileManager.addDefault("message.header", "&7--------------------------");
        fileManager.addDefault("message.message", "&7[&bBroadcast&7] &r%bcmsg%");
        fileManager.addDefault("message.footer", "&7--------------------------");
        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}