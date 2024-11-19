package umc.study.repository.QReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;
    private final QUser user = QUser.user;
    private final QRestaurant restaurant = QRestaurant.restaurant;

    @Override
    public void insertReview(Long userId, Long restaurantId, String content, Integer rating, List<Image> imageList) {

        User reviewUser = jpaQueryFactory
                .selectFrom(user)
                .where(user.id.eq(userId))
                .fetchOne();

        Restaurant reviewedRestaurant = jpaQueryFactory
                .selectFrom(restaurant)
                .where(restaurant.id.eq(restaurantId))
                .fetchOne();

        jpaQueryFactory.insert(review)
                .columns(review.user, review.restaurant, review.content, review.rating, review.imageList)
                .values(reviewUser, reviewedRestaurant, content, rating, imageList);
    }
}
