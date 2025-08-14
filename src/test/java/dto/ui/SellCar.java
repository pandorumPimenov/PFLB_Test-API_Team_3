package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

@Data
@AllArgsConstructor
@Builder
public class SellCar {
    @Default
    String UserId = "";

    @Default
    String CarId = "";
}