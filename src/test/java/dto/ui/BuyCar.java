package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BuyCar {
    String UserId;
    String CarId;
}
