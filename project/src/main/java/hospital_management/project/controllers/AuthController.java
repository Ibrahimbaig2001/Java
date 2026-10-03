package hospital_management.project.controllers;

import hospital_management.project.dto.UserRegisterRequestDto;
import hospital_management.project.dto.UserRegisterResponseDto;
import hospital_management.project.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequest){
        UserRegisterResponseDto response = authService.register(userRegisterRequest);
        return ResponseEntity.ok(response);
    }

}
