package hospital_management.project.service;

import hospital_management.project.dto.StaffRegistrationRequestDto;
import hospital_management.project.dto.StaffRegistrationResponseDto;
import hospital_management.project.dto.UserRegisterRequestDto;
import hospital_management.project.dto.UserRegisterResponseDto;
import hospital_management.project.models.Role;
import hospital_management.project.models.User;
import hospital_management.project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDto register(UserRegisterRequestDto userRegister){
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setEmail(userRegister.getEmail());
        String encodedPassword = passwordEncoder.encode(userRegister.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.PATIENT);
        User savedUser = userRepository.save(user);
        UserRegisterResponseDto response = new UserRegisterResponseDto();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        return response;
    }
    public StaffRegistrationResponseDto registerStaff(StaffRegistrationRequestDto staffRegistration){
        if (staffRegistration.getRole() == Role.PATIENT || staffRegistration.getRole() == Role.ADMIN){
            throw new RuntimeException("This endpoint cannot create PATIENT or ADMIN accounts");
        }
        if(userRepository.findByEmail(staffRegistration.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists.");
        }
        User user = new User();
        user.setUsername(staffRegistration.getUsername());
        user.setEmail(staffRegistration.getEmail());
        String encodedPassword = passwordEncoder.encode(staffRegistration.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(staffRegistration.getRole());
        User savedUser = userRepository.save(user);
        StaffRegistrationResponseDto response = new StaffRegistrationResponseDto();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setEnabled(savedUser.getEnabled());
        return response;

    }

}
