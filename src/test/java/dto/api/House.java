package dto.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Data
@Builder
public class House {
    @SerializedName("id")
    @Expose
    @Builder.Default
    private Integer id = 0;

    @SerializedName("floorCount")
    @Expose
    @Builder.Default
    private Integer floorCount = 1;

    @SerializedName("price")
    @Expose
    @Builder.Default
    private Integer price = 0;

    @SerializedName("parkingPlaces")
    @Expose
    @Builder.Default
    private List<ParkingPlace> parkingPlaces = List.of(ParkingPlace.builder()
            .id(2)
            .isWarm(true)
            .isCovered(false)
            .placesCount(412)
            .build());

    @SerializedName("lodgers")
    @Expose
    @Builder.Default
    private List<Lodger> lodgers = List.of(Lodger.builder().id(32)
            .firstName("Tom")
            .secondName("O`Nil")
            .age(44)
            .sex("MALE")
            .build());
}
