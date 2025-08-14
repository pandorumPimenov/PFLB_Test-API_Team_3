package tests.api;

import adapters.UserAdapter;
import dto.api.User;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends BaseAPITest {

    public static long id;
    @Test
    public void createUser() {
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
    }

    @Test
    public void getUser() {
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
        int id = rs.getId();
        Assert.assertEquals(rs.getFirstName(), "Jo");
        User get = userAdapter.getUser(id);
        Assert.assertEquals(get.getFirstName(), "Jo");
    }

    @Test
    public void deleteUser() {
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
        int id = rs.getId();
        Assert.assertEquals(rs.getFirstName(), "Jo");
        userAdapter.deleteUser(id);
    }

    @Test
    public void updateUser() {
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
        int id = rs.getId();
        Assert.assertEquals(rs.getFirstName(), "Jo");
        User user1 = User.builder()
                .id(24)
                .age(20)
                .sex("MALE")
                .firstName("Jo Jo")
                .secondName("MO")
                .money(20000).build();
        User update = userAdapter.updateUser(user1, id);
        Assert.assertEquals(update.getFirstName(), "Jo Jo");
    }
}
