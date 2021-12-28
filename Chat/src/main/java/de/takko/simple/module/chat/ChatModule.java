package de.takko.simple.module.chat;

import de.takko.simple.manager.base.ModuleInfo;
import de.takko.simple.manager.base.SimpleManager;
import de.takko.simple.manager.base.SimpleModule;
import de.takko.simple.manager.base.util.file.FileManager;
import org.bukkit.Server;

public class ChatModule extends SimpleModule {

    public ChatModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
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
    public void terminate() {}

    private void initConfig() {
        fileManager.addDefault("enabled", true);

        //Owner 1
        fileManager.addDefault("owner", "simple.chat.owner");
        fileManager.addDefault("owner", "&7| &4Inhaber &7• &4%name% &7≫ &a%msg%");
        //Sr.Admin 2
        fileManager.addDefault("sr_admin", "simple.chat.sr_admin");
        fileManager.addDefault("sr_admin", "&7| &4Sr.Admin &7• &4%name% &7≫ &a%msg%");
        //Admin 3
        fileManager.addDefault("admin", "simple.chat.admin");
        fileManager.addDefault("admin", "&7| &cAdmin &7• &c%name% &7≫ &2%msg%");
        //Developer 4
        fileManager.addDefault("developer", "simple.chat.developer");
        fileManager.addDefault("developer", "&7| &bDeveloper &7• &b%name% &7≫ &b%msg%");
        //Sr.Moderator 5
        fileManager.addDefault("sr_moderator", "simple.chat.sr_moderator");
        fileManager.addDefault("sr_moderator", "&7| &2Sr.Moderator &7• &2%name% &7≫ &2%msg%");
        //Moderator 6
        fileManager.addDefault("moderator", "simple.chat.moderator");
        fileManager.addDefault("moderator", "&7| &aModerator &7• &a%name% &7≫ &d%msg%");
        //Supporter 7
        fileManager.addDefault("supporter", "simple.chat.supporter");
        fileManager.addDefault("supporter", "&7| &1Supporter &7• &1%name% &7≫ &1%msg%");
        //Test-Supporter 8
        fileManager.addDefault("t_supporter", "simple.chat.t_supporter");
        fileManager.addDefault("t_supporter", "&7| &9T-Supporter &7• &9%player% &7≫ &9%msg%");
        //Builder 9
        fileManager.addDefault("builder", "simple.chat.builder");
        fileManager.addDefault("builder", "&7| &bBuilder &7• &e%name% &7≫ &e%msg%");
        //Partner 10
        fileManager.addDefault("partner", "simple.chat.partner");
        fileManager.addDefault("partner", "&7| &dPartner &7• &d%name% &7≫ &d%msg%");
        //Youtuber 11
        fileManager.addDefault("youtuber", "simple.chat.youtuber");
        fileManager.addDefault("youtuber", "&7| &5Youtuber &7• &5%name% &7≫ &5%msg%");
        //Twitch 12
        fileManager.addDefault("twitch", "simple.chat.twitch");
        fileManager.addDefault("twitch", "&7| &5Twitch &7• &5%name% &7≫ &5%msg%");
        //Premium Plus 13
        fileManager.addDefault("premium_plus", "simple.chat.premium_plus");
        fileManager.addDefault("premium_plus", "&7| &ePremiumPlus &7• &e%name% &7≫ &e%msg%");
        //Premium 14
        fileManager.addDefault("premium", "simple.chat.premium");
        fileManager.addDefault("premium", "&7| &6Premium &7• &6%name% &7≫ &6%msg%");
        //Spieler | default  15
        fileManager.addDefault("spieler", "simple.chat.spieler");
        fileManager.addDefault("spieler", "&7| &7Spieler &7• &7%name% &7≫ &7%msg%");
        //User Rang 1 16
        fileManager.addDefault("user_rank_1", "simple.chat.user_rank_1");
        fileManager.addDefault("user_rank_1", "%msg%");
        //User Rang 2 17
        fileManager.addDefault("user_rank_2", "simple.chat.user_rank_2");
        fileManager.addDefault("user_rank_2", "%msg%");
        //User Rang 3 18
        fileManager.addDefault("user_rank_3", "simple.chat.user_rank_3");
        fileManager.addDefault("user_rank_3", "%msg%");
        //User Rang 4 19
        fileManager.addDefault("user_rank_4", "simple.chat.user_rank_4");
        fileManager.addDefault("user_rank_4", "%msg%");
        //User Rang 5 20
        fileManager.addDefault("user_rank_5", "simple.chat.user_rank_5");
        fileManager.addDefault("user_rank_5", "%msg%");
        //User Rang 6 21
        fileManager.addDefault("user_rank_6", "simple.chat.user_rank_6");
        fileManager.addDefault("user_rank_6", "%msg%");
        //User Rang 7 22
        fileManager.addDefault("user_rank_7", "simple.chat.user_rank_7");
        fileManager.addDefault("user_rank_7", "%msg%");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}