package de.takko.simple.module.warp;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.file.FileManager;
import de.takko.simple.module.warp.command.WarpCommand;
import de.takko.simple.module.warp.command.WarpsCommand;
import de.takko.simple.module.warp.command.WarpManagerCommand;
import de.takko.simple.module.warp.util.Warp;
import org.bukkit.Server;

import java.util.ArrayList;
import java.util.List;

public class WarpModule extends SimpleModule {

    public WarpModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;
    private static List<Warp> warps = new ArrayList<>();
    private static WarpManager warpManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.warp.admin");

        initConfig();

        warpManager = new WarpManager();
        warpManager.loadWarps();

        registerCommand("warps").setExecutor(new WarpsCommand());
        registerCommand("warp").setExecutor(new WarpCommand());
        registerCommand("warpm", "warpmanager").setExecutor(new WarpManagerCommand());
    }

    @Override
    public void terminate() {

    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bWarps&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");
        fileManager.addDefault("NoWarps", "&cEs gibt noch keine Warps!");
        fileManager.addDefault("warps", "&aAlle Warps&7: &e%warps%");
        fileManager.addDefault("syntax", "&cNutz den Befehl richtig xD");
        fileManager.addDefault("not_exists", "&cDieser Warp existiert nictht!");
        fileManager.addDefault("separator", "&7, &e");
        fileManager.addDefault("teleport", "&aDu wurdest zu &e%warp% &ateleportiert.");
        fileManager.addDefault("add", "&aDu hast den Warp &e%warp% &aerstellt.");
        fileManager.addDefault("remove", "&aDu hast den Warp &e%warp% &aentfernt.");
        fileManager.addDefault("update", "&aDu hast den Warp &e%warp% &ageupdated.");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static List<Warp> getWarpList() {
        return warps;
    }

    public static WarpManager getWarpManager() {
        return warpManager;
    }
}