package de.takko.simple.manager.base.util;

import lombok.Getter;

import java.sql.*;

@Getter
public class MySQL {

    private final String url;
    private String user;
    private String password;

    private Connection connection;
    private final Logger logger = new Logger();

    public MySQL(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(this.url, this.user, this.password);
            logger.custom(Logger.ModuleType.MYSQL, "§aMySQL connected.");
            createTable();
        } catch (SQLException e) {
            logger.custom(Logger.ModuleType.MYSQL, "§cError while connect to MySQL!" + "\n" + e.getMessage());
        }
    }

    public void close() {
        try {
            if (isConnected()) {
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
                connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS MONEY (UUID VARCHAR(100), MONEY int)");
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