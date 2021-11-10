package de.takko.simple.module.homes;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.FileManager;
import de.takko.simple.module.homes.command.HomeCommand;
import de.takko.simple.module.homes.command.HomesCommand;
import de.takko.simple.module.homes.listener.HomeListener;
import de.takko.simple.module.homes.manager.HomeManager;
import de.takko.simple.module.homes.util.Home;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class HomesModule extends SimpleModule {

    public HomesModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;
    private static HashMap<Player, List<Home>> homesList = new HashMap<>();

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.homes.admin");

        initConfig();

        registerListener(new HomeListener());

        registerCommand("home").setExecutor(new HomeCommand());
        registerCommand("homes").setExecutor(new HomesCommand());

        Bukkit.getOnlinePlayers().forEach(player -> {
            HomeManager homeManager = new HomeManager(player);
            homeManager.saveHomes();
            homeManager.loadHomes();
        });
    }

    @Override
    public void terminate() {}

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bHomes&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");
        fileManager.addDefault("NoHomes", "&cDu hast keine Homes!");

        fileManager.addDefault("home.teleport", "&aDu wurdest zu &e%home% &ateleportiert.");
        fileManager.addDefault("home.not_exists", "&cDieser Home existiert nicht!");
        fileManager.addDefault("home.already_exists", "&cDieser Home existiert bereits!");

        fileManager.addDefault("home.add", "&aDu hast den Home &e%home% &aerstellt.");
        fileManager.addDefault("home.remove", "&aDu hast den Home &e%home% &aentfernt.");
        fileManager.addDefault("home.update", "&aDu hast die Position von &e%home% &age\u00E4ndert");

        fileManager.addDefault("syntax", "&aNutz den Befehl richtig du KEK.");
        fileManager.addDefault("homes.format", "&aDeine Homes&7: &e%homes%");
        fileManager.addDefault("homes.seperator", "&7, &e");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static HashMap<Player, List<Home>> getHomesList() {
        return homesList;
    }
}