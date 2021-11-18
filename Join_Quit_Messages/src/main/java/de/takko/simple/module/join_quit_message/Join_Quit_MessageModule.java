package de.takko.simple.module.join_quit_message;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.file.FileManager;
import org.bukkit.Server;

public class Join_Quit_MessageModule extends SimpleModule {

    public Join_Quit_MessageModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.j_q_m.admin");

        initConfig();

        registerListener(new JQListener());
    }

    @Override
    public void terminate() {}

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bJoin & Quit Message&7] &r");

        fileManager.addDefault("join.enabled", true);
        fileManager.addDefault("join.message", "%prefix% &e%player% &ahat den Server betreten.");

        fileManager.addDefault("quit.enabled", true);
        fileManager.addDefault("quit.message", "%prefix% &e%player% &ahat den Server verlassen.");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}