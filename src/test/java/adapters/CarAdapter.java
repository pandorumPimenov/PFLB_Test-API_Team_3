package adapters;

import dto.api.Car;
import io.restassured.mapper.ObjectMapperType;

public class CarAdapter extends BaseAdapter {

    public Car createCar(Car car) {
        getToken();
        return spec
                .body(gson.toJson(car))
                .header("Authorization", "Bearer " + token)
                .when()
                .post(BASE_URI + "car")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(Car.class);
    }

    public Car getCar(long id) {
        getToken();
        return spec
                .when()
                .header("Authorization", "Bearer " + token)
                .get(BASE_URI + "car/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Car.class);
    }

    public Car deleteCar(long id) {
        getToken();
        return spec
                .when()
                .header("Authorization", "Bearer " + token)
                .delete(BASE_URI + "car/" + id)
                .then()
                .log().all()
                .statusCode(204)
                .extract()
                .as(Car.class, ObjectMapperType.GSON);
    }

    public Car updateCar(Car car, long id) {
        getToken();
        return spec
                .body(gson.toJson(car))
                .when()
                .header("Authorization", "Bearer " + token)
                .put(BASE_URI + "car/" + id)
                .then()
                .log().all()
                .statusCode(202)
                .extract()
                .as(Car.class);
    }
}
