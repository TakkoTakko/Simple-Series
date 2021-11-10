package de.takko.simple.module.time;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.FileManager;
import de.takko.simple.module.time.command.DayCommand;
import de.takko.simple.module.time.command.NightCommand;
import org.bukkit.Server;

public class TimeModule extends SimpleModule {

    public TimeModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.time.admin");

        initConfig();

        registerCommand("day").setExecutor(new DayCommand());
        registerCommand("night").setExecutor(new NightCommand());
    }

    @Override
    public void terminate() {

    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bTime&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");
        fileManager.addDefault("permissions.day", "simple.time.day");
        fileManager.addDefault("permissions.night", "simple.time.night");
        fileManager.addDefault("messages.day", "&aDu hast die Tageszeit gewechselt.");
        fileManager.addDefault("messages.night", "&aDu hast die Tageszeit gewechselt.");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}