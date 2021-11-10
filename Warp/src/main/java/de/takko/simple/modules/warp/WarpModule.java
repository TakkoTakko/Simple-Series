package de.takko.simple.modules.warp;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.utils.FileManager;
import de.takko.simple.modules.warp.utils.Warp;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class WarpModule extends SimpleModule {

    public WarpModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;
    private static List<Warp> warps = new ArrayList<>();

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.warp.admin");

        initConfig();
    }

    @Override
    public void terminate() {

    }

    private void initConfig() {


        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static List<Warp> getWarpList() {
        return warps;
    }
}