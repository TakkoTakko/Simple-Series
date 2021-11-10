package de.takko.simple.module.fly;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.FileManager;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlyModule extends SimpleModule {

    public FlyModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;
    private static List<Player> loggedIn = new ArrayList<>();

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.fly.admin");

        initConfig();

        registerListener(new FlyListener());
        registerCommand("fly").setExecutor(new FlyCommand());
    }

    @Override
    public void terminate() {}

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bFly&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("OfflinePlayer", "&cDieser Spieler ist offline");
        fileManager.addDefault("ConsoleReplacement", "Konsole");
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
    public static List<Player> getLoggedIn() {
        return loggedIn;
    }
}