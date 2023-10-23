package trip.planner.dto.user;


import lombok.*;

@Data
public class UserRegistrationDto {
    private long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String country;
}
