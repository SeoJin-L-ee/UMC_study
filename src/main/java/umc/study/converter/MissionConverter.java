package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.web.dto.missionDTO.MissionRequestDTO;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.CreateMissionDTO createMissionDTO) {

        return Mission.builder()
                .rewardPercent(createMissionDTO.getRewardPercent())
                .minimumMoney(createMissionDTO.getMinimumMoney())
                .deadline(createMissionDTO.getDeadline())
                .build();
    }
}
