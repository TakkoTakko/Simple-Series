package de.takko.simple.manager.base.util;

import org.bukkit.Bukkit;

public class Logger {

    public enum LogType {
        INFO, WARNING, ERROR, SPACE, LOADING, LOADED, UNLOADING
    }

    public enum ModuleType {
        MYSQL,
    }

    public void log(LogType type, String message) {
        switch (type) {
            case INFO:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimpleManager§7] §7[§aINFO§7] §r" + message);
                break;
            case WARNING:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimpleManager§7] §7[§6WARNING§7] §r" + message);
                break;
            case ERROR:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimpleManager§7] §7[§cERROR§7] §r" + message);
                break;
            case SPACE:
                Bukkit.getConsoleSender().sendMessage("");
                break;
            case LOADING:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimple-Modules§7] §7[§2LOADING§7] §r" + message);
                break;
            case LOADED:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimple-Modules§7] §7[§aLOADED§7] §r" + message);
                break;
            case UNLOADING:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimple-Modules§7] §7[§aUNLOADING§7] §r" + message);
                break;
        }
    }
    public void custom(ModuleType type, String message) {
        switch (type) {
            case MYSQL:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimple-Modules§7] §7[§bMySQL§7] §r" + message);
                break;
        }
    }
}