package umc.study.service.ReviewService.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.apiPayload.exception.handler.UserHandler;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.repository.RestaurantRepository2;
import umc.study.repository.ReviewRepository;
import umc.study.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository2 restaurantRepository;

    private static final int PAGE_SIZE = 10;

    @Override
    public Page<Review> getByUserId(Long userId, Integer page) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        return reviewRepository.findByUser(user, PageRequest.of(page, PAGE_SIZE));
    }

    @Override
    public Page<Review> getReviewList(Long restaurantId, Integer page) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        return reviewRepository.findAllByRestaurant(restaurant, PageRequest.of(page, PAGE_SIZE));
    }
}
