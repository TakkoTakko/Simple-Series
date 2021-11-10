package de.takko.simple.module.warp.util;

import lombok.Getter;
import org.bukkit.Location;

@Getter
public class Warp {

    private Location location;
    private String name;

    public Warp(Location location, String name) {
        this.location = location;
        this.name = name;
    }
}