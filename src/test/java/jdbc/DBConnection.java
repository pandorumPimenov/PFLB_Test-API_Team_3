package jdbc;

import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;

import java.sql.*;
import java.util.Properties;

@Slf4j
public class DBConnection {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet result = null;

    private static final DbConfig cfg = ConfigFactory.create(DbConfig.class);

    private static final String USER = cfg.dbUser(),
            PASSWORD = cfg.dbPassword(),
            URL = cfg.dbUrl();

    public void connect() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);
            connect = DriverManager.getConnection(URL, properties);
            log.info("Connection DB");
            statement = connect.createStatement();
        } catch (SQLException e) {
            log.error("Содение к БД не установлено");
            e.printStackTrace();
        }
    }

    public ResultSet select(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            if (connect != null) {
                log.info("Connection close");
                connect.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        } catch (Exception ignore) {
        }
    }
}
