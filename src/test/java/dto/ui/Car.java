package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

@Data
@AllArgsConstructor
@Builder
public class Car {
    @Default
    public String engineType = "GASOLINE";

    @Default
    public String mark = "";

    @Default
    public String model = "";

    @Default
    public String price = "0";
}