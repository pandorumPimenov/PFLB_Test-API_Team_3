package dto.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class ParkingPlace {
    @SerializedName("id")
    @Expose
    @Builder.Default
    private Integer id = 0;

    @SerializedName("isWarm")
    @Expose
    @Builder.Default
    private Boolean isWarm = false;

    @SerializedName("isCovered")
    @Expose
    @Builder.Default
    private Boolean isCovered = false;

    @SerializedName("placesCount")
    @Expose
    @Builder.Default
    private Integer placesCount = 1;
}
