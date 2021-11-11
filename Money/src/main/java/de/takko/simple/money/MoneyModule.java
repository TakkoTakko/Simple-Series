package de.takko.simple.money;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.FileManager;
import de.takko.simple.money.command.MoneyCommand;
import de.takko.simple.money.listener.MoneyListener;
import de.takko.simple.money.util.sql.MySQL;
import org.bukkit.Server;

public class MoneyModule extends SimpleModule {
    
    public MoneyModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;
    private static MySQL mySQL;
    public static boolean SQL = false;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.money.admin");
        mySQL = new MySQL("localhost", "3306", "root", "pw");

        initConfig();

        if (SQL) {
            mySQL.connect();
        }

        registerListener(new MoneyListener());
        registerCommand("money").setExecutor(new MoneyCommand());
    }

    @Override
    public void terminate() {
        mySQL.close();
    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bMoney&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");
        fileManager.addDefault("OfflinePlayer", "&cDieser Spieler ist offline");
        fileManager.addDefault("ConsoleReplacement", "Konsole");
        fileManager.addDefault("defaultMoney", 100);

        fileManager.addDefault("permission", "simple.money.admin");
        fileManager.addDefault("showMoney", "&aDu besitzt: &e%money%");
        fileManager.addDefault("setMoney", "&aDein Kontostand wurde von &e%player% &aauf &e%money% &agesetzt.");
        fileManager.addDefault("addMoney", "&aDein Kontostand wurde von &e%player% &aauf &e%money% &aerh\u00F6ht.");
        fileManager.addDefault("removeMoney", "&aDein Kontostand wurde von &e%player% &aauf &e%money% &aherunter gesetzt.");

        fileManager.addDefault("syntax", "&cNutz den Befehl richtig :P");

        fileManager.saveDefaults();
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
    public static MySQL getMySQL() {
        return mySQL;
    }
}