package dto.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class Car {
    @SerializedName("engineType")
    @Expose
    @Builder.Default
    private String engineType = "GASOLINE";

    @SerializedName("id")
    @Expose
    @Builder.Default
    private Integer id = 0;

    @SerializedName("mark")
    @Expose
    @Builder.Default
    private String mark = "";

    @SerializedName("model")
    @Expose
    @Builder.Default
    private String model = "";

    @SerializedName("price")
    @Expose
    @Builder.Default
    private Double price = 0.0;
}