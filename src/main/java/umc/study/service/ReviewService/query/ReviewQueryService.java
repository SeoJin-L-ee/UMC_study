package umc.study.service.ReviewService.query;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;

public interface ReviewQueryService {

    Page<Review> getByUserId(Long userId, Integer page);
    Page<Review> getReviewList(Long restaurantId, Integer page);
}
