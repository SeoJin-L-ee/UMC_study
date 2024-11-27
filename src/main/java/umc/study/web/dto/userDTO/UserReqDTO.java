package umc.study.web.dto.userDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.Role;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class UserReqDTO {

    @Getter
    @Setter
    public static class CreateUserReqDTO {
        @NotBlank
        private String name;
        @NotNull
        private Gender gender;
        @Size(min = 5, max = 12)
        private String address;
        @NotNull
        @Email
        private String email;
        @NotBlank
        private String password;
        @ExistCategories
        private List<String> preferCategory;
        @NotNull
        Role role;
    }
}
