package umc.study.repository.MissionRepository;

import com.querydsl.core.Tuple;
import umc.study.domain.Mission;
import umc.study.domain.Restaurant;

import java.util.List;

public interface MissionRepositoryCustom {

    List<Tuple> findUserMissions(Long userId, Long lastMissionId);

    List<Tuple> findLessDeadLineMissions(Long userId, Long lastMissionId);
}
