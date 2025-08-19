package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.api.AuthResponse;
import dto.api.Login;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import tests.ui.BaseTest;
import static io.restassured.RestAssured.given;

public class BaseAdapter extends BaseTest {

    protected final String BASE_URI = "http://82.142.167.37:4879/";

    protected static String token;

    public Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public RequestSpecification spec =
            given()
                    .contentType(ContentType.JSON)
                    .log().all();

    public void getToken() {
        AuthorizationAdapter authorizationAdapter = new AuthorizationAdapter();
        Login loginRequest = Login.builder()
                .username(user)
                .password(password)
                .build();
        AuthResponse response = authorizationAdapter.authorization(loginRequest);

        token = response.getAccessToken();

    }

}
