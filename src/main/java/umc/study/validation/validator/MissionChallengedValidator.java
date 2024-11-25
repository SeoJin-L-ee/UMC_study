package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.service.MissionService.command.MissionCommandService;
import umc.study.validation.annotation.ChallengedMission;
import umc.study.web.dto.ChallengeValidationDTO;

@Component
@RequiredArgsConstructor
public class MissionChallengedValidator implements ConstraintValidator<ChallengedMission, ChallengeValidationDTO> {

    private final MissionCommandService missionCommandService;

    @Override
    public void initialize(ChallengedMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ChallengeValidationDTO dto, ConstraintValidatorContext context) {

        boolean isValid = !missionCommandService.isAlreadyChallenged(dto.getUserId(), dto.getMissionId());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_IS_CHALLENGED.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
