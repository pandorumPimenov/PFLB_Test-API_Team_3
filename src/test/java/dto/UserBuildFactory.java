package dto;

import com.github.javafaker.Faker;
import dto.ui.UserBuild;

public class UserBuildFactory {
    public static final Faker faker = new Faker();

    public static UserBuild getRandomUserBuild() {
        return UserBuild.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age(String.valueOf(faker.number().numberBetween(1, 123)))
                .money(String.valueOf(faker.number().numberBetween(0, 10000)))
                .build();
    }

    public static UserBuild getUserWithMinAge() {
        return UserBuild.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age("1")
                .money(String.valueOf(faker.number().numberBetween(0, 10000)))
                .build();
    }

    public static UserBuild getUserWithMaxAge() {
        return UserBuild.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age("123")
                .money(String.valueOf(faker.number().numberBetween(0, 10000)))
                .build();
    }

    public static UserBuild getUserWithEmptyFirstName() {
        return UserBuild.builder()
                .firstName("")
                .lastName(faker.name().lastName())
                .age(String.valueOf(faker.number().numberBetween(1, 123)))
                .money(String.valueOf(faker.number().numberBetween(0, 10000)))
                .build();
    }

    public static UserBuild getUserWithSpecialCharsInLastName() {
        return UserBuild.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName() + "123!@#")
                .age(String.valueOf(faker.number().numberBetween(1, 123)))
                .money(String.valueOf(faker.number().numberBetween(0, 10000)))
                .build();
    }

    public static UserBuild getUserWithZeroAge() {
        return UserBuild.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age("0")
                .money(String.valueOf(faker.number().numberBetween(0, 10000)))
                .build();
    }

    public static UserBuild getUserWithNegativeAge() {
        return UserBuild.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age("-1")
                .money(String.valueOf(faker.number().numberBetween(0, 10000)))
                .build();
    }
}
