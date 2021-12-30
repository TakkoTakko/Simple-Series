package de.takko.simple.module.fly;

import de.takko.simple.manager.base.ModuleInfo;
import de.takko.simple.manager.base.SimpleManager;
import de.takko.simple.manager.base.SimpleModule;
import de.takko.simple.manager.base.util.file.FileManager;
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
        fileManager.addDefault("permission", "simple.fly.use");
        fileManager.addDefault("ConsoleReplacement", "Konsole");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");

        fileManager.addDefault("self", "&aDu hast dein Flugmodus gewechselt.");
        fileManager.addDefault("other.self", "&aDu hast den Flugmodus von &e%player% &agewechselt.");
        fileManager.addDefault("other.other", "&aDein Flugmodus wurde von &e%player% &agewechselt.");

        fileManager.addDefault("syntax", "&cBitte benutze&7: &6/fly &7(&6Player&7)");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
    public static List<Player> getLoggedIn() {
        return loggedIn;
    }
}