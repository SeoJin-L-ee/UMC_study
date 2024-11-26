package umc.study.service.MissionService.command;

import umc.study.web.dto.missionDTO.MissionReqDTO;

public interface MissionCommandService {

    boolean isAlreadyChallenged(Long userId, Long missionId);

    void createMission(Long restaurantId, MissionReqDTO.CreateMissionDTO createMissionDTO);
}
