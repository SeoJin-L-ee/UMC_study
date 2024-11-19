package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.reviewDTO.ReviewRequestDTO;
import umc.study.web.dto.reviewDTO.ReviewResponseDTO;

public class ReviewConverter {

    public static ReviewResponseDTO.CreateReviewResDTO toCreateReviewResDTO(Review review) {

        return ReviewResponseDTO.CreateReviewResDTO.builder()
                .build();
    }

    public static Review toReview(ReviewRequestDTO.CreateReviewReqDTO createReviewReqDTO) {

        return Review.builder()
                .content(createReviewReqDTO.getContent())
                .rating(createReviewReqDTO.getRating())
                .build();
    }
}
