package tests.api;

import adapters.UserAdapter;
import dto.api.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest {
    long id;

    @Test(priority = 1)
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
