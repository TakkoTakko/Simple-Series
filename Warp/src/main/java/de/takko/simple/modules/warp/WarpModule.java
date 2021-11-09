package de.takko.simple.modules.warp;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.utils.FileManager;
import de.takko.simple.modules.warp.commands.WarpCommand;
import de.takko.simple.modules.warp.commands.WarpManagerCommand;
import de.takko.simple.modules.warp.commands.WarpsCommand;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class WarpModule extends SimpleModule {

    public WarpModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;
    private static WarpManager warpManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.warp.admin");

        initConfig();

        warpManager = new WarpManager();

        registerCommand("warp").setExecutor(new WarpCommand());
        registerCommand("warps").setExecutor(new WarpsCommand());
        registerCommand("warpm").setExecutor(new WarpManagerCommand());
    }

    @Override
    public void terminate() {

    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bGamemode&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");

        fileManager.addDefault("permission", "simple.warps.admin");

        fileManager.addDefault("warp.syntax", "&cBitte benutze: &6/warp &7<&6Warp&7>");
        fileManager.addDefault("warp.warp", "&aDu wurdest zum Warp &e%warp% &ateleportiert.");
        fileManager.addDefault("warp.not_exists", "&cDieser Warp existiert nicht.");

        fileManager.addDefault("warps.message", "&aAlle Warps: &e%warps%");
        fileManager.addDefault("warps.seperate", "&a, &e");
        fileManager.addDefault("warps.nowarps", "&cEs gibt noch keine Warps.");
        
        fileManager.addDefault("warpm.syntax", "&cBitte benutze: &6/warpm &7<&6create&7, &6delete&7> <&6Warp&7>");
        fileManager.addDefault("warpm.not_exists", "&cDer zu l\u00F6schende Warp existiert nicht.");
        fileManager.addDefault("warpm.add", "&aDer Warp &e%warp% &awurde erfolgreich erstellt.");
        fileManager.addDefault("warpm.remove", "&aDer Warp &e%warp% &awurde erfolgreich gel\u00F6scht.");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
    public static WarpManager getWarpManager() {
        return warpManager;
    }
}