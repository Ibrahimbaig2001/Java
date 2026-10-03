package hospital_management.project.dto;

import hospital_management.project.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterResponseDto {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private boolean enabled;
}
