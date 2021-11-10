package de.takko.simple.modules.teamchat;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.utils.FileManager;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamchatModule extends SimpleModule {

    public TeamchatModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.teamchat.admin");

        initConfig();

        registerCommand("teamchat", "tc").setExecutor(new TeamchatCommand());
    }

    @Override
    public void terminate() {

    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bTeamchat&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("OfflinePlayer", "&cDieser Spieler ist offline");
        fileManager.addDefault("console_replacement", "Konsole");
        fileManager.addDefault("format", "%prefix% &7» &e%player% &7» &r%msg%");
        fileManager.addDefault("permission", "simple.teamchat.use");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}