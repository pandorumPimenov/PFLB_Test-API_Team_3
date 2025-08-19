package adapters;

import dto.api.User;
import io.restassured.mapper.ObjectMapperType;

public class UserAdapter extends BaseAdapter {

    public User createUser(User user) {
        getToken();
        return spec
                .body(gson.toJson(user))
                .header("Authorization", "Bearer " + token)
                .when()
                .post(BASE_URI + "user")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(User.class);
    }

    public User getUser(long id) {
        getToken();
        return spec
                .when()
                .header("Authorization", "Bearer " + token)
                .get(BASE_URI + "user/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(User.class);
    }

    public User deleteUser(long id) {
        getToken();
        return spec
                .when()
                .header("Authorization", "Bearer " + token)
                .delete(BASE_URI + "user/" + id)
                .then()
                .log().all()
                .statusCode(204)
                .extract()
                .as(User.class, ObjectMapperType.GSON);
    }

    public User updateUser(User user, long id) {
        getToken();
        return spec
                .body(gson.toJson(user))
                .when()
                .header("Authorization", "Bearer " + token)
                .put(BASE_URI + "user/" + id)
                .then()
                .log().all()
                .statusCode(202)
                .extract()
                .as(User.class);
    }

}
