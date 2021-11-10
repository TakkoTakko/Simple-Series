package de.takko.simple.modules.gamemode;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.utils.FileManager;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class GameModeModule extends SimpleModule {
    
    public GameModeModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }
    
    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.enderchest.admin");
    
        initConfig();
        
        registerCommand("gamemode", "gm").setExecutor(new GameModeCommand());
    }

    @Override
    public void terminate() {

    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bGamemode&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");
        fileManager.addDefault("OfflinePlayer", "&cDieser Spieler ist offline");
        fileManager.addDefault("ConsoleReplacement", "Konsole");

        fileManager.addDefault("permission.gamemode.0", "simple.gamemode.0");
        fileManager.addDefault("permission.gamemode.1", "simple.gamemode.1");
        fileManager.addDefault("permission.gamemode.2", "simple.gamemode.2");
        fileManager.addDefault("permission.gamemode.3", "simple.gamemode.3");

        fileManager.addDefault("message.self.0", "&aDu hast &edich &ain den Survivalmodus geaddzt.");
        fileManager.addDefault("message.self.1", "&aDu hast &edich &ain den Creativemodus geaddzt.");
        fileManager.addDefault("message.self.2", "&aDu hast &edich &ain den Abenteuermodus geaddzt.");
        fileManager.addDefault("message.self.3", "&aDu hast &edich &ain den Zuschauermodus geaddzt.");
        fileManager.addDefault("message.other.self.0", "&aDu hast &e%name% &ain den &eSurvivalmodus geaddzt.");
        fileManager.addDefault("message.other.self.1", "&aDu hast &e%name% &ain den &eCreativemodus geaddzt.");
        fileManager.addDefault("message.other.self.2", "&aDu hast &e%name% &ain den &eAbenteuermodus geaddzt.");
        fileManager.addDefault("message.other.self.3", "&aDu hast &e%name% &ain den &eZuschauermodus geaddzt.");
        fileManager.addDefault("message.other.other.0", "&aDu wurdest von &e%name% &ain den &eSurvivalmodus &ageaddzt.");
        fileManager.addDefault("message.other.other.1", "&aDu wurdest von &e%name% &ain den &eCreativemodus &ageaddzt.");
        fileManager.addDefault("message.other.other.2", "&aDu wurdest von &e%name% &ain den &eAbenteuermodus &ageaddzt.");
        fileManager.addDefault("message.other.other.3", "&aDu wurdest von &e%name% &ain den &eZuschauermodus &ageaddzt.");
        fileManager.addDefault("message.error", "&cWenn du dich selber in einen anderen Spielmodus veraddzten willst \n Benutze: &6/gamemode &7<&60&7,&61&7,&62&7,&63&7>");
        fileManager.addDefault("message.syntax", "&cBitte benutze: &6/gamemode &7<&60&7,&61&7,&62&7,&63&7> <&6Spieler&7>");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}