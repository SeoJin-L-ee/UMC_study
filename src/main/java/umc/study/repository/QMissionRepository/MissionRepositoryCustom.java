package umc.study.repository.QMissionRepository;

import com.querydsl.core.Tuple;

import java.util.List;

public interface MissionRepositoryCustom {

    List<Tuple> findUserMissions(Long userId, Long lastMissionId);

    List<Tuple> findLessDeadLineMissions(Long userId, Long lastMissionId);
}
