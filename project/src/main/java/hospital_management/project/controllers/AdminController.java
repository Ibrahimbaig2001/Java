package hospital_management.project.controllers;

import hospital_management.project.dto.StaffRegistrationRequestDto;
import hospital_management.project.dto.StaffRegistrationResponseDto;
import hospital_management.project.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AuthService authService;
    public AdminController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/users")
    public ResponseEntity<StaffRegistrationResponseDto> registerStaff(@RequestBody StaffRegistrationRequestDto request){
        StaffRegistrationResponseDto responseDto = authService.registerStaff(request);
        return ResponseEntity.ok(responseDto);
    }
}
