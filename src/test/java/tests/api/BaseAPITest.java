package tests.api;

import adapters.AuthorizationAdapter;
import dto.api.AuthResponse;
import dto.api.Login;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;


public class BaseAPITest extends BaseTest {

    protected static String token;
@BeforeMethod
    @Test
    public void testAuthorization() {
        AuthorizationAdapter authorizationAdapter = new AuthorizationAdapter();
        Login loginRequest = Login.builder()
                .username(user)
                .password(password)
                .build();
        AuthResponse response = authorizationAdapter.authorization(loginRequest);

        token = response.getAccessToken();

    }

}
