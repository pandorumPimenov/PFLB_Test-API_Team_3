package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.api.User;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import tests.api.BaseAPITest;

import static io.restassured.RestAssured.given;

public class UserAdapter extends BaseAPITest {

    private final String BASE_URI = "http://82.142.167.37:4879/";
    public Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public RequestSpecification spec = given()
            .contentType(ContentType.JSON)
            .log().all();

    public User createUser(User user) {
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

    public User getUser(int id) {
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

    public User deleteUser(int id) {
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

    public User updateUser(User user, int id) {
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
