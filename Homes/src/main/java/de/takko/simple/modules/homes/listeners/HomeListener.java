package de.takko.simple.modules.homes.listeners;

import de.takko.simple.modules.homes.HomesModule;
import de.takko.simple.modules.homes.utils.Home;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeListener implements Listener {

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        File file = new File(HomesModule.getFileManager().getModulePath() + "/homes/", player.getUniqueId() + ".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        List<Home> homes = new ArrayList<>();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (file.length() != 0) {
                for (String key : cfg.getKeys(false)) {
                    World world = Bukkit.getWorld(cfg.getString(key + ".loc.world"));
                    double x = cfg.getDouble(key + ".loc.x"),
                            y = cfg.getDouble(key + ".loc.y"),
                            z = cfg.getDouble(key + ".loc.z"),
                            yaw = cfg.getDouble(key + ".loc.yaw"),
                            pitch = cfg.getDouble(key + ".loc.pitch");
                    Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
                    homes.add(new Home(key, cfg.getString(key + ".owner"), cfg.getString(key + ".name"), loc));
                }
                HomesModule.getHomesList().put(player, homes);
            }
        }
    }

    @EventHandler
    public void handle(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        File file = new File(HomesModule.getFileManager().getModulePath() + "/homes/", player.getUniqueId() + ".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (HomesModule.getHomesList().containsKey(player)) {
            try {
                for (Home home : HomesModule.getHomesList().get(player)) {
                    cfg.set(home.getUniqueID() + ".name", home.getName());
                    cfg.set(home.getUniqueID() + ".owner", home.getOwner());

                    cfg.set(home.getUniqueID() + ".loc.home", home.getLocation().getWorld().getName());
                    cfg.set(home.getUniqueID() + ".loc.x", home.getLocation().getX());
                    cfg.set(home.getUniqueID() + ".loc.y", home.getLocation().getY());
                    cfg.set(home.getUniqueID() + ".loc.z", home.getLocation().getZ());
                    cfg.set(home.getUniqueID() + ".loc.yaw", home.getLocation().getYaw());
                    cfg.set(home.getUniqueID() + ".loc.pitch", home.getLocation().getX());
                }
                try {
                    cfg.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                HomesModule.getHomesList().remove(player);
            }
        }
    }
}