package hospital_management.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto {
    private String username;
    private String email;
    private String password;
}
