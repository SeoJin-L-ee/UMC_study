package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.reviewDTO.ReviewReqDTO;
import umc.study.web.dto.reviewDTO.ReviewResDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResDTO toCreateReviewResDTO(Review review) {

        return ReviewResDTO.CreateReviewResDTO.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getId())
                .restaurantId(review.getRestaurant().getId())
                .build();
    }

    public static Review toReview(ReviewReqDTO.CreateReviewReqDTO createReviewReqDTO) {

        return Review.builder()
                .content(createReviewReqDTO.getContent())
                .rating(createReviewReqDTO.getRating())
                .build();
    }

    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review){

        return ReviewResDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }
    public static ReviewResDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewList){

        List<ReviewResDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewPreviewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }
}
