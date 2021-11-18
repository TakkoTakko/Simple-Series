package de.takko.simple.money;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.file.FileManager;
import de.takko.simple.manager.util.MySQL;
import de.takko.simple.money.command.MoneyCommand;
import de.takko.simple.money.listener.MoneyListener;
import org.bukkit.Server;

public class MoneyModule extends SimpleModule {

    public MoneyModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private static FileManager fileManager;
    private static MySQL mySQL;
    public static boolean sql;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", "simple.money.admin");

        initConfig();

        this.sql = Boolean.parseBoolean(fileManager.getWithout("mysql.enabled"));
        if (sql) {
            this.mySQL = new MySQL(MoneyModule.getFileManager().getWithout("mysql.url"),
                    MoneyModule.getFileManager().getWithout("mysql.user"),
                    MoneyModule.getFileManager().getWithout("mysql.password"));
            mySQL.connect();
        }
        registerListener(new MoneyListener());
        registerCommand("money").setExecutor(new MoneyCommand(this));
    }

    @Override
    public void terminate() {
        if (mySQL != null) {
            mySQL.close();
        }
    }

    private void initConfig() {
        fileManager.addDefault("Prefix", "&7[&bMoney&7] &r");
        fileManager.addDefault("NoPerm", "&cDazu hast du keine Rechte!");
        fileManager.addDefault("NoPlayer", "&cDu musst ein Spieler sein!");
        fileManager.addDefault("OfflinePlayer", "&cDieser Spieler ist offline");
        fileManager.addDefault("ConsoleReplacement", "Konsole");
        fileManager.addDefault("defaultMoney", 100);

        fileManager.addDefault("mysql.enabled", true);
        fileManager.addDefault("mysql.url", "jdbc:mysql://localhost:3306/money?autoReconnect=true");
        fileManager.addDefault("mysql.user", "root");
        fileManager.addDefault("mysql.password", "password");

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