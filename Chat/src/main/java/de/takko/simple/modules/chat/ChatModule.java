package de.takko.simple.modules.chat;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.utils.FileManager;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatModule extends SimpleModule {

    public ChatModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.chat.*");

        initConfig();

        registerListener(new ChatListener());
    }

    @Override
    public void terminate() {

    }

    private void initConfig() {
        fileManager.addDefault("enabled", true);

        //Owner 1
        fileManager.addDefault("owner", "lobby.owner");
        fileManager.addDefault("owner", "&7| &4Inhaber &7• &4%name% &7≫ &a%msg%");
        //Sr.Admin 2
        fileManager.addDefault("sr_admin", "lobby.sr_admin");
        fileManager.addDefault("sr_admin", "&7| &4Sr.Admin &7• &4%name% &7≫ &a%msg%");
        //Admin 3
        fileManager.addDefault("admin", "lobby.admin");
        fileManager.addDefault("admin", "&7| &cAdmin &7• &c%name% &7≫ &2%msg%");
        //Developer 4
        fileManager.addDefault("developer", "lobby.developer");
        fileManager.addDefault("developer", "&7| &bDeveloper &7• &b%name% &7≫ &b%msg%");
        //Sr.Moderator 5
        fileManager.addDefault("sr_moderator", "lobby.sr_moderator");
        fileManager.addDefault("sr_moderator", "&7| &2Sr.Moderator &7• &2%name% &7≫ &2%msg%");
        //Moderator 6
        fileManager.addDefault("moderator", "lobby.moderator");
        fileManager.addDefault("moderator", "&7| &aModerator &7• &a%name% &7≫ &d%msg%");
        //Supporter 7
        fileManager.addDefault("supporter", "lobby.supporter");
        fileManager.addDefault("supporter", "&7| &1Supporter &7• &1%name% &7≫ &1%msg%");
        //Test-Supporter 8
        fileManager.addDefault("t_supporter", "lobby.t_supporter");
        fileManager.addDefault("t_supporter", "&7| &9T-Supporter &7• &9%player% &7≫ &9%msg%");
        //Builder 9
        fileManager.addDefault("builder", "lobby.builder");
        fileManager.addDefault("builder", "&7| &bBuilder &7• &e%name% &7≫ &e%msg%");
        //Partner 10
        fileManager.addDefault("partner", "lobby.partner");
        fileManager.addDefault("partner", "&7| &dPartner &7• &d%name% &7≫ &d%msg%");
        //Youtuber 11
        fileManager.addDefault("youtuber", "lobby.youtuber");
        fileManager.addDefault("youtuber", "&7| &5Youtuber &7• &5%name% &7≫ &5%msg%");
        //Twitch 12
        fileManager.addDefault("twitch", "lobby.twitch");
        fileManager.addDefault("twitch", "&7| &5Twitch &7• &5%name% &7≫ &5%msg%");
        //Premium Plus 13
        fileManager.addDefault("premium_plus", "lobby.premium_plus");
        fileManager.addDefault("premium_plus", "&7| &ePremiumPlus &7• &e%name% &7≫ &e%msg%");
        //Premium 14
        fileManager.addDefault("premium", "lobby.premium");
        fileManager.addDefault("premium", "&7| &6Premium &7• &6%name% &7≫ &6%msg%");
        //Spieler | default  15
        fileManager.addDefault("spieler", "lobby.spieler");
        fileManager.addDefault("spieler", "&7| &7Spieler &7• &7%name% &7≫ &7%msg%");
        //User Rang 1 16
        fileManager.addDefault("user_rank_1", "lobby.user_rank_1");
        fileManager.addDefault("user_rank_1", "%msg%");
        //User Rang 2 17
        fileManager.addDefault("user_rank_2", "lobby.user_rank_2");
        fileManager.addDefault("user_rank_2", "%msg%");
        //User Rang 3 18
        fileManager.addDefault("user_rank_3", "lobby.user_rank_3");
        fileManager.addDefault("user_rank_3", "%msg%");
        //User Rang 4 19
        fileManager.addDefault("user_rank_4", "lobby.user_rank_4");
        fileManager.addDefault("user_rank_4", "%msg%");
        //User Rang 5 20
        fileManager.addDefault("user_rank_5", "lobby.user_rank_5");
        fileManager.addDefault("user_rank_5", "%msg%");
        //User Rang 6 21
        fileManager.addDefault("user_rank_6", "lobby.user_rank_6");
        fileManager.addDefault("user_rank_6", "%msg%");
        //User Rang 7 22
        fileManager.addDefault("user_rank_7", "lobby.user_rank_7");
        fileManager.addDefault("user_rank_7", "%msg%");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}