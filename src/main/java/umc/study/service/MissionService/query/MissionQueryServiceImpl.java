package umc.study.service.MissionService.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.domain.Mission;
import umc.study.domain.Restaurant;
import umc.study.repository.MissionRepository;
import umc.study.repository.RestaurantRepository2;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final RestaurantRepository2 restaurantRepository;

    private static final int PAGE_SIZE = 10;

    @Override
    public Page<Mission> getByRestaurant(Long restaurantId, Integer page) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));
        return missionRepository.findByRestaurant(restaurant, PageRequest.of(page, PAGE_SIZE));
    }
}
