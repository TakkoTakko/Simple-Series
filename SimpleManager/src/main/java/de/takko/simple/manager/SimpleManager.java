package de.takko.simple.manager;

import com.google.common.base.Joiner;
import de.takko.simple.manager.command.InfoCommand;
import de.takko.simple.manager.util.Logger;
import de.takko.simple.manager.util.Utils;
import de.takko.simple.manager.util.file.ManagerConfig;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SimpleManager extends JavaPlugin {

    private final static Set<SimpleModule> moduleSet = new HashSet<>();

    private Logger logger;

    private static JavaPlugin javaPlugin;
    private static ManagerConfig managerConfig;

    @Getter
    private File modulesFolder;

    @Override
    public void onEnable() {
        javaPlugin = this;
        managerConfig = new ManagerConfig();
        logger = new Logger();

        logger.log(Logger.LogType.INFO, "§aStarting SimpleManager...");

        Utils.sleep(375);

        getCommand("info").setExecutor(new InfoCommand());

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

        loadModules(this.modulesFolder);

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

            ClassLoader classLoader = simpleModule.getClass().getClassLoader();
            if (classLoader instanceof ModuleLoader) {
                ModuleLoader loader = (ModuleLoader) simpleModule.getClass().getClassLoader();
                try {
                    logger.log(Logger.LogType.INFO, "§aClosing class loader");
                    loader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.moduleSet.clear();
    }

    private void initFiles() {
        if (!getDataFolder().isDirectory()) {
            getDataFolder().mkdirs();
        }

        this.modulesFolder = new File(getDataFolder(), "modules");
        if (!this.modulesFolder.isDirectory()) {
            this.modulesFolder.mkdirs();
        }
    }


    private void loadModules(File modulesFolder) {
        File[] files = modulesFolder.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
        if (files == null || files.length == 0) {
            logger.log(Logger.LogType.WARNING, "§7No modules to load.");
            return;
        }

        for (File file : files) {
            try {
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

    public static JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public static Set<SimpleModule> getModules() {
        return moduleSet;
    }
    public static ManagerConfig getManagerConfig() {
        return managerConfig;
    }
}