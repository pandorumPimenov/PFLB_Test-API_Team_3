package tests.api;

import adapters.UserAdapter;
import dto.api.User;
import jdbc.DBConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserTest {
    long id;

    @Test(priority = 1)
    public void createUser() throws SQLException {
        UserAdapter userAdapter = new UserAdapter();
        User user = User.builder()
                .id(24)
                .age(20)
                .sex("MALE")
                .firstName("Jo")
                .secondName("MO")
                .money(20000)
                .build();
        User rs = userAdapter.createUser(user);
        id = rs.getId();
        Assert.assertEquals(rs.getFirstName(), "Jo");
        DBConnection connection = new DBConnection();//DB
        connection.connect();
        ResultSet res = connection.select("SELECT COUNT(" + id + ") FROM car WHERE id = " + id);
        if (res != null) {
            ResultSet result = connection.select("SELECT * FROM person WHERE id = " + id);
            while (result.next()) {
                log.info("output new user");
                System.out.println("id - " + result.getInt("id"));
                System.out.println("age - " + result.getInt("age"));
                System.out.println("first_name - " + result.getString("first_name"));
                System.out.println("second_name - " + result.getString("second_name"));
                System.out.println("money - " + result.getDouble("money"));
            }
        } else {
            log.info("id absent");
        }
        connection.close();
    }

    @Test(priority = 2)
    public void getUser() {
        UserAdapter userAdapter = new UserAdapter();
        User get = userAdapter.getUser(id);
        Assert.assertEquals(get.getFirstName(), "Jo");
    }

    @Test(priority = 4)
    public void deleteUser() {
        UserAdapter userAdapter = new UserAdapter();
        userAdapter.deleteUser(id);
    }

    @Test(priority = 3)
    public void updateUser() {
        UserAdapter userAdapter = new UserAdapter();
        User user = User.builder()
                .id(24)
                .age(20)
                .sex("MALE")
                .firstName("Jo Jo")
                .secondName("MO")
                .money(20000).build();
        User update = userAdapter.updateUser(user, id);
        Assert.assertEquals(update.getFirstName(), "Jo Jo");
    }
}
