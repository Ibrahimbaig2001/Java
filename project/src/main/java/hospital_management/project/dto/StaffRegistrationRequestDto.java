package hospital_management.project.dto;

import hospital_management.project.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffRegistrationRequestDto {
    private String username;
    private String email;
    private String password;
    private Role role;

}
