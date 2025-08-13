package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Cars {
    public String engineType;
    public String mark;
    public String model;
    public String price;
}
