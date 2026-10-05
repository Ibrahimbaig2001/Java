package in.strikes.authdemo.service;

import in.strikes.authdemo.dto.UserRegisterRequestDto;
import in.strikes.authdemo.dto.UserRegisterResponseDto;
import in.strikes.authdemo.entity.Role;
import in.strikes.authdemo.entity.User;
import in.strikes.authdemo.repository.RoleRepository;
import in.strikes.authdemo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto){
        User user = new User();
        user.setUsername(userRegisterRequestDto.getUsername());
        String encodedPassword = passwordEncoder.encode(userRegisterRequestDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Role role = roleRepository.findByName("ROLE_USER").get();
        user.getRoles().add(role);

        userRepository.save(user);
        UserRegisterResponseDto userRegisterResponseDto = new UserRegisterResponseDto();
        userRegisterResponseDto.setUsername(user.getUsername());
        userRegisterResponseDto.setMessage("User successfully registered");
        return  userRegisterResponseDto;

    }
}
