package de.takko.simple.modules.homes;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.utils.FileManager;
import de.takko.simple.modules.homes.commands.HomeCommand;
import de.takko.simple.modules.homes.commands.HomesCommand;
import de.takko.simple.modules.homes.listeners.HomeListener;
import de.takko.simple.modules.homes.utils.Home;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public class HomesModule extends SimpleModule {

    public HomesModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
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
    }

    @Override
    public void terminate() {}

    private void initConfig() {

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static HashMap<Player, List<Home>> getHomesList() {
        return homesList;
    }
}