package umc.study.service.MissionService.query;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;

public interface MissionQueryService {

    Page<Mission> getByRestaurant(Long restaurantId, Integer page);
}
