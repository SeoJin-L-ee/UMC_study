package umc.study.repository.QReviewRepository;

import umc.study.domain.Image;

import java.util.List;

public interface ReviewRepositoryCustom {

    void insertReview(Long userId, Long restaurantId, String content, Integer rating, List<Image> imageList);
}
