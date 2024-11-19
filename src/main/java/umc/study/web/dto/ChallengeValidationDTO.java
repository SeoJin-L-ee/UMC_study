package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;
import umc.study.validation.annotation.ChallengedMission;

@Getter
@Builder
public class ChallengeValidationDTO {

    private Long userId;
    private Long missionId;
}
