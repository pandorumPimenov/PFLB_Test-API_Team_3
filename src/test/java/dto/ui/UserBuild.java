package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserBuild {
    @Default
    String firstName = "";

    @Default
    String lastName = "";

    @Default
    String age = "0";

    @Default
    String money = "0";
}
