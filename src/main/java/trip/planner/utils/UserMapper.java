package trip.planner.utils;

import org.springframework.stereotype.Component;
import trip.planner.dto.user.UserDto;
import trip.planner.entity.User;

@Component
public class UserMapper {

    public UserDto toDTO (User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setCountry(user.getCountry());
        return dto;
    }

}
