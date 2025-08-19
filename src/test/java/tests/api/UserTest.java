package tests.api;

import adapters.UserAdapter;
import dto.api.User;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import jdbc.DBConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Owner("Андреев Дмитрий")
public class UserTest {
    long id;

    @Test(priority = 1,
            description = "Создание нового пользователя с проверкой в БД")
    @Description("Тест создает пользователя с параметрами: имя 'Jo', фамилия 'MO', возраст 20, баланс 20000. "
            + "После создания проверяет данные в таблице person через JDBC.")
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

    @Test(priority = 2,
            description = "Получение данных пользователя по ID")
    @Description("Проверка корректности получения данных пользователя через API. Ожидаемое имя - 'Jo'.")
    public void getUser() {
        UserAdapter userAdapter = new UserAdapter();
        User get = userAdapter.getUser(id);
        Assert.assertEquals(get.getFirstName(), "Jo");
    }

    @Test(priority = 4,
            description = "Удаление пользователя")
    @Description("Проверка удаления пользователя по ID через API.")
    public void deleteUser() {
        UserAdapter userAdapter = new UserAdapter();
        userAdapter.deleteUser(id);
    }

    @Test(priority = 3,
            description = "Обновление данных пользователя")
    @Description("Изменение имени пользователя с 'Jo' на 'Jo Jo' и проверка обновления через API.")
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
