package umc.study.repository.QRestaurantRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
}
// 리뷰 작성하는 쿼리, * 사진의 경우는 일단 배제