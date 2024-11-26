package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.missionDTO.MissionReqDTO;
import umc.study.web.dto.missionDTO.MissionResDTO;

import java.util.List;

public class MissionConverter {

    public static Mission toMission(MissionReqDTO.CreateMissionDTO createMissionDTO) {

        return Mission.builder()
                .rewardPercent(createMissionDTO.getRewardPercent())
                .minimumMoney(createMissionDTO.getMinimumMoney())
                .deadline(createMissionDTO.getDeadline())
                .build();
    }

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missions) {

        List<MissionResDTO.MissionPreviewDTO> missionPreviewDTOs = missions.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .toList();

        return MissionResDTO.MissionPreviewListDTO.builder()
                .isLast(missions.isLast())
                .isFirst(missions.isFirst())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .listSize(missionPreviewDTOs.size())
                .reviewList(missionPreviewDTOs)
                .build();
    }

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {

        return MissionResDTO.MissionPreviewDTO.builder()
                .restaurantName(mission.getRestaurant().getName())
                .rewardPercent(mission.getRewardPercent())
                .minimumMoney(mission.getMinimumMoney())
                .deadline(mission.getDeadline())
                .build();
    }

    // UserMission -> MissionPreviewList
    public static MissionResDTO.MissionPreviewListDTO toUserMissionPreviewListDTO(Page<UserMission> userMissions) {

        List<MissionResDTO.MissionPreviewDTO> missionPreviewDTOs = userMissions.stream()
                .map(MissionConverter::toUSerMissionPreviewDTO)
                .toList();

        return MissionResDTO.MissionPreviewListDTO.builder()
                .isLast(userMissions.isLast())
                .isFirst(userMissions.isFirst())
                .totalPage(userMissions.getTotalPages())
                .totalElements(userMissions.getTotalElements())
                .listSize(missionPreviewDTOs.size())
                .reviewList(missionPreviewDTOs)
                .build();
    }

    public static MissionResDTO.MissionPreviewDTO toUSerMissionPreviewDTO(UserMission userMission) {

        return MissionResDTO.MissionPreviewDTO.builder()
                .restaurantName(userMission.getMission().getRestaurant().getName())
                .rewardPercent(userMission.getMission().getRewardPercent())
                .minimumMoney(userMission.getMission().getMinimumMoney())
                .deadline(userMission.getMission().getDeadline())
                .build();
    }
}
