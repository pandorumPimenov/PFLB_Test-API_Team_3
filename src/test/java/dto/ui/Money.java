package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Money {
    @Default
    String userId = "";

    @Default
    String money = "0";
}
