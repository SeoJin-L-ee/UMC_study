package umc.study.web.dto.reviewDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.domain.Image;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewReqDTO {
        @NotBlank
        private String content;
        @NotNull
        private Integer rating;
        private List<Image> imageList;
    }
}
