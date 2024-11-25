package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.MissionService.command.MissionCommandService;
import umc.study.web.dto.missionDTO.MissionRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{restaurantId}/missions")
    @Operation(summary = "가게에 미션 추가하기 API")
    public ApiResponse<String> createMission(
            @PathVariable Long restaurantId,
            @RequestBody MissionRequestDTO.CreateMissionDTO createMissionDTO) {

        missionCommandService.createMission(restaurantId, createMissionDTO);
        return ApiResponse.onSuccess("미션이 성공적으로 추가되었습니다.");
    }
}
