package umc.study.web.dto.missionDTO;

import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class CreateMissionDTO {
        private Integer rewardPercent;
        private Integer minimumMoney;
        private LocalDateTime deadline;
    }
}
