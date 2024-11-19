package umc.study.service.MissionService.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.apiPayload.exception.handler.UserHandler;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Restaurant;
import umc.study.domain.User;
import umc.study.repository.MissionRepository;
import umc.study.repository.QRestaurantRepository.RestaurantRepository;
import umc.study.repository.UserMissionRepository;
import umc.study.repository.UserRepository;
import umc.study.web.dto.missionDTO.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public boolean isAlreadyChallenged(Long userId, Long missionId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        return userMissionRepository.existsByUserAndMission(user, mission);
    }

    @Override
    public void createMission(Long restaurantId, MissionRequestDTO.CreateMissionDTO createMissionDTO) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));
        Mission mission = MissionConverter.toMission(createMissionDTO);

        mission.setRestaurant(restaurant);
        missionRepository.save(mission);
    }
}
