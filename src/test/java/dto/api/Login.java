package dto.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class Login {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
}
