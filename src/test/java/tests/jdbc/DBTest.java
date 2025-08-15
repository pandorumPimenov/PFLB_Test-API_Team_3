package tests.jdbc;

import jdbc.DBConnection;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DBTest {
    long valueId = tests.api.UserTest.id;

    @Test
    public void checkUser() throws SQLException {

        DBConnection connection = new DBConnection();
        connection.connect();
        ResultSet res = connection.select("SELECT COUNT(" + valueId + ") FROM car WHERE id = " + valueId);
        if (res != null) {
            ResultSet result = connection.select("SELECT * FROM person WHERE id = 7337");
            while (result.next()) {
                System.out.print(result.getInt("id") + " ");
                System.out.print(result.getInt("age") + " ");
                System.out.print(result.getString("first_name") + " ");
                System.out.print(result.getString("second_name") + " ");
                System.out.println(result.getDouble("money") + " ");
            }
        }else {
            log.info("id absent");
        }
        connection.close();
    }
}
