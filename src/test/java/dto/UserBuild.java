package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserBuild {
    String firstName;
    String lastName;
    String age;
    String money;
}
