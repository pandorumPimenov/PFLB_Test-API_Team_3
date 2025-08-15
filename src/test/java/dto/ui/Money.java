package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

@Data
@AllArgsConstructor
@Builder
public class Money {
    @Default
    String userId = "";

    @Default
    String money = "0";
}