package adapters;

import dto.api.House;
import io.restassured.mapper.ObjectMapperType;

public class HouseAdapter extends BaseAdapter {

    public House createHouse(House house) {
        getToken();
        return spec
                .body(gson.toJson(house))
                .header("Authorization", "Bearer " + token)
                .when()
                .post(BASE_URI + "house")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(House.class);
    }

    public House getHouse(long id) {
        getToken();
        return spec
                .when()
                .header("Authorization", "Bearer " + token)
                .get(BASE_URI + "house/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(House.class);
    }

    public House deleteHouse(long id) {
        getToken();
        return spec
                .when()
                .header("Authorization", "Bearer " + token)
                .delete(BASE_URI + "house/" + id)
                .then()
                .log().all()
                .statusCode(204)
                .extract()
                .as(House.class, ObjectMapperType.GSON);
    }

    public House updateHouse(House house, long id) {
        getToken();
        return spec
                .body(gson.toJson(house))
                .when()
                .header("Authorization", "Bearer " + token)
                .put(BASE_URI + "house/" + id)
                .then()
                .log().all()
                .statusCode(202)
                .extract()
                .as(House.class);
    }
}
