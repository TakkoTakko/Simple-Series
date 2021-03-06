package de.takko.simple.module.spawn;

import de.takko.simple.manager.base.ModuleInfo;
import de.takko.simple.manager.base.SimpleManager;
import de.takko.simple.manager.base.SimpleModule;
import de.takko.simple.manager.base.util.Logger;
import de.takko.simple.manager.base.util.file.FileManager;
import de.takko.simple.manager.base.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class SpawnModule extends SimpleModule {

    public SpawnModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;
    public static Location spawn;
    private static Logger logger;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.spawn.admin");
        logger = new Logger();

        initConfig();

        registerCommand("spawn").setExecutor(new SpawnCommand());
        registerCommand("setspawn").setExecutor(new SetSpawnCommand());

        loadLocation();
    }

    @Override
    public void terminate() {}

    private void loadLocation() {
        if (!fileManager.getFile().exists()) {
            fileManager.createFile();
        }
        if (fileManager.getWithoutReplacements("location.world") == null) {
            logger.log(Logger.LogType.WARNING, "§7No spawn set");
            return;
        }
        World world = Bukkit.getWorld(fileManager.getWithout("location.world"));
        Double  x = Double.valueOf(fileManager.getWithout("location.x")),
                y = Double.valueOf(fileManager.getWithout("location.y")),
                z = Double.valueOf(fileManager.getWithout("location.z"));
        Float   yaw = Float.valueOf(fileManager.getWithout("location.yaw")),
                pitch = Float.valueOf(fileManager.getWithout("location.pitch"));
        spawn = new Location(world, x, y, z, yaw, pitch);
    }

    public static boolean isSpawnSet() {
        return spawn != null;
    }

    public static void save(Location location) {
        File file = fileManager.getFile();
        FileConfiguration cfg = fileManager.getConfiguration();
        cfg.set("location.world", location.getWorld().getName());
        cfg.set("location.x", location.getX());
        cfg.set("location.y", location.getY());
        cfg.set("location.z", location.getZ());
        cfg.set("location.yaw", location.getY());
        cfg.set("location.pitch", location.getPitch());
        Utils.saveFile(file, cfg);
    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bSpawn&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");
        fileManager.addDefault("OfflinePlayer", "&cDieser Spieler ist offline");
        fileManager.addDefault("teleportOnJoin", true);

        fileManager.addDefault("permission", "simple.spawn.set");
        fileManager.addDefault("NoSpawn", "&cDer Spawn wurde noch nicht gesetzt.");
        fileManager.addDefault("SpawnSet", "&aDer Spawn wurde erfolgreich gesetzt.");
        fileManager.addDefault("teleport", "&aDu wurdest zum Spawn teleportiert.");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}