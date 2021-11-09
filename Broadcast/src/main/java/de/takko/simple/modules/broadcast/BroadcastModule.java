package de.takko.simple.modules.broadcast;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.utils.FileManager;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class BroadcastModule extends SimpleModule {

    public BroadcastModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.enderchest.admin");

        initConfig();

        PluginCommand command = registerCommand("broadcast", "bc");
        command.setExecutor(new BroadcastCommand());
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