package jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Properties;

@Slf4j
public class DBConnection {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet result = null;
    private static final String URL = "jdbc:postgresql://82.142.167.37:4832/pflb_trainingcenter",
            USER = "pflb-at-read",
            PASSWORD = "PflbQaTraining2354";

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
                //System.out.println("Закрытие соединения");
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
