package trip.planner.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trip.planner.dto.user.UserCredentialsDto;
import trip.planner.dto.user.UserDto;
import trip.planner.dto.user.UserRegistrationDto;
import trip.planner.entity.User;
import trip.planner.repository.UserRepository;
import trip.planner.utils.UserMapper;

import java.util.Optional;
@Log4j2
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDTO) {
        User user = new User();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setName(userRegistrationDTO.getName());
        user.setSurname(userRegistrationDTO.getSurname());
        user.setCountry(userRegistrationDTO.getCountry());
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserInfo(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    @Override
    public boolean authUser(UserCredentialsDto userCredentialsDTO) {
        log.info("EMAIL:" + userCredentialsDTO.getEmail());
        log.info("PASS: " + userCredentialsDTO.getPassword());
        User user = userRepository.findByEmail(userCredentialsDTO.getEmail());
        log.info(user);
        if (user != null) {
            return passwordEncoder.matches(userCredentialsDTO.getPassword(), user.getPassword());
        }
        return false;
    }
}
