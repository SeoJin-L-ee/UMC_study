package umc.study.web.dto.userDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.domain.enums.Gender;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class UserReqDTO {

    @Getter
    public static class CreateUserReqDTO {
        @NotBlank
        private String name;
        @NotNull
        private Gender gender;
        @Size(min = 5, max = 12)
        private String address;
        @NotNull
        private String email;
        @ExistCategories
        private List<String> preferCategory;
    }
}
