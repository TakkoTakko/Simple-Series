package de.takko.simple.manager;

import com.google.common.base.Joiner;
import de.takko.simple.manager.utils.Logger;
import de.takko.simple.manager.utils.Utils;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleManager extends JavaPlugin {

    private final Set<SimpleModule> moduleSet = new HashSet<>();

    private List<File> files = new ArrayList<>();
    private Logger logger;

    @Override
    public void onEnable() {
        logger = new Logger();

        logger.log(Logger.LogType.INFO, "§aStarting SimpleManager...");

        Utils.sleep(375);

        logger.log(Logger.LogType.SPACE, null);
        logger.log(Logger.LogType.INFO, "§aInitializing files...");
        logger.log(Logger.LogType.SPACE, null);

        Utils.sleep(750);
        initFiles();

        logger.log(Logger.LogType.INFO, "§aAll files initialized");
        logger.log(Logger.LogType.SPACE, null);
        logger.log(Logger.LogType.INFO, "§aLoading modules...");
        logger.log(Logger.LogType.SPACE, null);
        Utils.sleep(750);

        loadModules();

        logger.log(Logger.LogType.INFO, "§aLoaded all modules.");

        Utils.sleep(375);

        logger.log(Logger.LogType.SPACE, null);
        logger.log(Logger.LogType.INFO, "§aSimpleManager started successfully.");

    }

    @Override
    public void onDisable() {
        for (SimpleModule simpleModule : this.moduleSet) {
            simpleModule.terminate();
            for (Listener listener : simpleModule.getListenerSet()) {
                HandlerList.unregisterAll(listener);
                logger.log(Logger.LogType.UNLOADING, "§e" + getDescription().getName() + " §ais shutting down.");
            }
        }
        this.moduleSet.clear();
    }

    private void initFiles() {
        if (!getDataFolder().isDirectory()) {
            getDataFolder().mkdirs();
        }

        File modules = new File(getDataFolder(), "modules");
        if (!modules.isDirectory()) {
            modules.mkdirs();
        }

        for (File file : modules.listFiles()) {
            if (file.isDirectory()) {
                continue;
            }
            files.add(file);
        }
    }


    private void loadModules() {
        if (files.size() == 0) {
            logger.log(Logger.LogType.WARNING, "§7No modules to load.");
            return;
        }
        for (File file : files) {
            try {
                if (file.isDirectory()) {
                    continue;
                }

                ModuleLoader loader = new ModuleLoader(new URL[]{file.toURL()}, getClassLoader(), this);

                //starting
                logger.log(Logger.LogType.LOADING, "§7Name: §e" + loader.getModuleInfo().getName());
                logger.log(Logger.LogType.LOADING, "§7Version: §e" + loader.getModuleInfo().getVersion());
                logger.log(Logger.LogType.LOADING, "§7Authors: §e" + Joiner.on("§7, §e").join(loader.getModuleInfo().getAuthors()));

                SimpleModule simpleModule = loader.newModule();
                this.moduleSet.add(simpleModule);
                simpleModule.init();

                //started
                logger.log(Logger.LogType.LOADED, "§e" + loader.getModuleInfo().getName() + " §asuccessfully started.");
                logger.log(Logger.LogType.SPACE, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}