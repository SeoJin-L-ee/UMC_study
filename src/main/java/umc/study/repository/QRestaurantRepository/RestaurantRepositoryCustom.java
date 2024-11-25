package umc.study.repository.QRestaurantRepository;

import umc.study.domain.Restaurant;

import java.util.List;

public interface RestaurantRepositoryCustom {

    List<Restaurant> dynamicQueryWithBooleanBuilder(String name, Float score);
}
