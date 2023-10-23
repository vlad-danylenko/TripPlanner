package trip.planner.service;

import trip.planner.dto.user.UserCredentialsDto;
import trip.planner.dto.user.UserDto;
import trip.planner.dto.user.UserRegistrationDto;

import java.util.Optional;

public interface UserService {
    void registerUser(UserRegistrationDto userRegistrationDTO);
    Optional<UserDto> getUserInfo(Long id);
    boolean authUser (UserCredentialsDto userCredentialsDTO);
}
