package umc.study.service.MissionService.command;

import umc.study.web.dto.missionDTO.MissionRequestDTO;

public interface MissionCommandService {

    boolean isAlreadyChallenged(Long userId, Long missionId);

    void createMission(Long restaurantId, MissionRequestDTO.CreateMissionDTO createMissionDTO);
}
