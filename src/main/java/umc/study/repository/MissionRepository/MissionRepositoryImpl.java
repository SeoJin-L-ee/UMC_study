package umc.study.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QMission;
import umc.study.domain.QRestaurant;
import umc.study.domain.mapping.QUserMission;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QUserMission userMission = QUserMission.userMission;
    private final QRestaurant restaurant = QRestaurant.restaurant;

    @Override
    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
    public List<Tuple> findUserMissions(Long userId, Long lastMissionId) {

        // 마지막으로 조회한 미션의 createdAt (서브쿼리)
        LocalDateTime lastCreatedAt = jpaQueryFactory
                .select(userMission.createdAt)
                .from(userMission)
                .where(userMission.id.eq(lastMissionId))
                .fetchOne();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(userMission.user.id.eq(userId));
        builder.and(userMission.isDone.eq(false)); // 진행 중인 미션 보기
        builder.and(userMission.createdAt.lt(lastCreatedAt));

        return jpaQueryFactory
                .select(userMission.user.id, restaurant.id, restaurant.name, mission.rewardPercent, mission.minimumMoney, userMission.isDone)
                .from(userMission)
                .innerJoin(userMission.mission, mission)
                .innerJoin(mission.restaurant, restaurant)
                .where(builder)
                .orderBy(userMission.createdAt.desc())
                .limit(5)
                .fetch();
    }

    @Override
    // 마감기한이 적게 남은 미션 순서로 페이징하여 출력
    public List<Tuple> findLessDeadLineMissions(Long userId, Long lastMissionId) {

        // 마지막으로 조회한 미션의 createdAt
        LocalDateTime lastCreatedAt = jpaQueryFactory
                .select(mission.createdAt)
                .from(mission)
                .where(mission.id.eq(lastMissionId))
                .fetchOne();

        // 마지막으로 조회한 미션의 deadline
        LocalDateTime lastDeadline = jpaQueryFactory
                .select(mission.deadline)
                .from(mission)
                .where(mission.id.eq(lastMissionId))
                .fetchOne();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(mission.deadline.gt(LocalDateTime.now())); // 진행 중인데
        builder.and(mission.deadline.lt(lastDeadline)); // 마지막에 조회한 미션보다 데드라인이 적게 남은
        builder.and(mission.createdAt.lt(lastCreatedAt)); // 근데 이제 최신순

        return jpaQueryFactory
                .select(restaurant.id, restaurant.name, mission.rewardPercent, mission.minimumMoney, mission.deadline)
                .from(mission)
                .innerJoin(mission.restaurant, restaurant)
                .where(builder)
                .limit(5)
                .fetch();
    }
}
