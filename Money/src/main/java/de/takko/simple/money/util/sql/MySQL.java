package de.takko.simple.money.util.sql;

import de.takko.simple.manager.util.Logger;
import lombok.Getter;

import java.sql.*;

@Getter
public class MySQL {

    private String host;
    private String database;
    private String user;
    private String password;

    private Connection connection;
    private final Logger logger;

    public MySQL(String host, String database, String user, String password) {
        this.logger = new Logger();

        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", user, password);
            logger.custom(Logger.ModuleType.MYSQL, "§aMySQL connected.");
            createTable();
        } catch (SQLException e) {
            logger.custom(Logger.ModuleType.MYSQL, "§cError while connect to MySQL!" + "\n" + e.getMessage());
        }
    }

    public void close() {
        try {
            if(connection != null) {
                connection.close();
                logger.custom(Logger.ModuleType.MYSQL, "&aMySQL connection closed.");
            }
        } catch (SQLException e) {
            logger.custom(Logger.ModuleType.MYSQL, "§cError while closing MySQL connection!" + "\n" + e.getMessage());
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void createTable() {
        if (isConnected()) {
            try {
                connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS MONEY (UUID VARCHAR(100), NAME VARCHAR(100), KILLS int, DEATHS int , KD DOUBLE, ACR int, COINS int)");
            } catch (SQLException e) {
                logger.custom(Logger.ModuleType.MYSQL, "§cError while creating database!" + "\n" + e.getMessage());
            }
        }
    }

    public void update(String qry) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            logger.custom(Logger.ModuleType.MYSQL, "§cError while 'update'!");
            logger.custom(Logger.ModuleType.MYSQL, "&aReconnecting...");
        }
    }

    public ResultSet getResult(String qry) {
        ResultSet rs = null;

        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            logger.custom(Logger.ModuleType.MYSQL, "§cError while 'query'!");
            logger.custom(Logger.ModuleType.MYSQL, "&aReconnecting...");
        }
        return rs;
    }
}