package de.takko.simple.manager.utils;

import org.bukkit.Bukkit;

public class Logger {

    public enum LogType {
        INFO, WARNING, ERROR, SPACE, LOADING, LOADED, UNLOADING
    }

    public void log(LogType type, String message) {
        switch (type) {
            case INFO:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimpleManager§7] §r§7[§aINFO§7] §r" + message);
                break;
            case WARNING:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimpleManager§7] §r§7[§6WARNING§7] §r" + message);
                break;
            case ERROR:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimpleManager§7] §r§7[§cERROR§7] §r" + message);
                break;
            case SPACE:
                Bukkit.getConsoleSender().sendMessage("");
                break;
            case LOADING:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimpleManager-Modules§7] §r§7[§2LOADING§7] §r" + message);
                break;
            case LOADED:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimpleManager-Modules§7] §r§7[§aLOADED§7] §r" + message);
                break;
            case UNLOADING:
                Bukkit.getConsoleSender().sendMessage("§7[§bSimpleManager-Modules§7] §r§7[§aUNLOADING§7] §r" + message);
                break;
        }
    }
}