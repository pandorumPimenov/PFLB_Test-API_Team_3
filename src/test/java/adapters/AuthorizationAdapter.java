package adapters;

import dto.api.AuthResponse;
import dto.api.Login;
import io.restassured.mapper.ObjectMapperType;


public class AuthorizationAdapter extends BaseAdapter {
    private final String BASE_URI = "http://82.142.167.37:4879/";


    public AuthResponse authorization(Login login) {
        return spec
                .body(gson.toJson(login))
                .when()
                .post(BASE_URI + "login")
                .then()
                .log().all()
                .statusCode(202)
                .extract()
                .as(AuthResponse.class, ObjectMapperType.GSON);
    }
}
