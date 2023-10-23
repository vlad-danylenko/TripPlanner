package trip.planner.dto.user;

import lombok.*;

@Data
public class UserDto {
    private long id;
    private String email;
    private String name;
    private String surname;
    private String country;
}
