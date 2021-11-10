package de.takko.simple.module.homes.util;


import lombok.Getter;
import org.bukkit.Location;

@Getter
public class Home {

    private String uniqueID;
    private String owner;
    private Location location;
    private String name;

    public Home(String uniqueID,String owner, String name, Location location) {
        this.uniqueID = uniqueID;
        this.location = location;
        this.owner = owner;
        this.name = name;
    }
}