package umc.study.service.UserMission.query;

import org.springframework.data.domain.Page;
import umc.study.domain.mapping.UserMission;

public interface UserMissionQueryService {

    Page<UserMission> getOngoingByUserId(Long userId, Integer page);
}
