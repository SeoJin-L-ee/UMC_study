package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Restaurant;

public interface RestaurantRepository2 extends JpaRepository<Restaurant, Long> {
}
