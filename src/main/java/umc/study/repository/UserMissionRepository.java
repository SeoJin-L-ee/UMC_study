package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    boolean existsByUserAndMission(User user, Mission mission);
    Page<UserMission> findByUserAndIsDoneIsFalse(User user, Pageable pageable);
}
