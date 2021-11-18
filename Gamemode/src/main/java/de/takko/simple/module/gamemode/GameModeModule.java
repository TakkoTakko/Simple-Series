package de.takko.simple.module.gamemode;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.file.FileManager;
import org.bukkit.Server;

public class GameModeModule extends SimpleModule {
    
    public GameModeModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
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

        fileManager.addDefault("message.self.0", "&aDu hast &edich &ain den Survivalmodus gesetzt.");
        fileManager.addDefault("message.self.1", "&aDu hast &edich &ain den Creativemodus gesetzt.");
        fileManager.addDefault("message.self.2", "&aDu hast &edich &ain den Abenteuermodus gesetzt.");
        fileManager.addDefault("message.self.3", "&aDu hast &edich &ain den Zuschauermodus gesetzt.");
        fileManager.addDefault("message.other.self.0", "&aDu hast &e%name% &ain den &eSurvivalmodus gesetzt.");
        fileManager.addDefault("message.other.self.1", "&aDu hast &e%name% &ain den &eCreativemodus gesetzt.");
        fileManager.addDefault("message.other.self.2", "&aDu hast &e%name% &ain den &eAbenteuermodus gesetzt.");
        fileManager.addDefault("message.other.self.3", "&aDu hast &e%name% &ain den &eZuschauermodus gesetzt.");
        fileManager.addDefault("message.other.other.0", "&aDu wurdest von &e%name% &ain den &eSurvivalmodus &agesetzt.");
        fileManager.addDefault("message.other.other.1", "&aDu wurdest von &e%name% &ain den &eCreativemodus &agesetzt.");
        fileManager.addDefault("message.other.other.2", "&aDu wurdest von &e%name% &ain den &eAbenteuermodus &agesetzt.");
        fileManager.addDefault("message.other.other.3", "&aDu wurdest von &e%name% &ain den &eZuschauermodus &agesetzt.");
        fileManager.addDefault("message.error", "&cWenn du dich selber in einen anderen Spielmodus versetzt willst \n Benutze: &6/gamemode &7<&60&7,&61&7,&62&7,&63&7>");
        fileManager.addDefault("message.syntax", "&cBitte benutze: &6/gamemode &7<&60&7,&61&7,&62&7,&63&7> <&6Spieler&7>");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}