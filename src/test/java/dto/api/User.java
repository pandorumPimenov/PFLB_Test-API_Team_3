package dto.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class User {
    @SerializedName("id")
    @Expose
    @Builder.Default
    private Integer id = 0;

    @SerializedName("firstName")
    @Expose
    @Builder.Default
    private String firstName = "";

    @SerializedName("secondName")
    @Expose
    @Builder.Default
    private String secondName = "";

    @SerializedName("age")
    @Expose
    @Builder.Default
    private Integer age = 0;

    @SerializedName("sex")
    @Expose
    @Builder.Default
    private String sex = "UNKNOWN";

    @SerializedName("money")
    @Expose
    @Builder.Default
    private Integer money = 0;
}
