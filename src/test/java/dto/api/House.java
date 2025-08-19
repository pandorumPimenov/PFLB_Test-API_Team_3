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
//    @Singular
    @Builder.Default
    private List<ParkingPlace> parkingPlaces = List.of();

    @SerializedName("lodgers")
    @Expose
//    @Singular
    @Builder.Default
    private List<Lodger> lodgers = List.of();
}
