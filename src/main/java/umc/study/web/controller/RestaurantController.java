package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.service.MissionService.command.MissionCommandService;
import umc.study.service.MissionService.query.MissionQueryService;
import umc.study.service.ReviewService.query.ReviewQueryService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistRestaurant;
import umc.study.web.dto.missionDTO.MissionReqDTO;
import umc.study.web.dto.missionDTO.MissionResDTO;
import umc.study.web.dto.reviewDTO.ReviewResDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
@Validated
public class RestaurantController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/{restaurantId}/missions")
    @Operation(summary = "가게에 미션 추가하기 API")
    public ApiResponse<String> createMission(
            @PathVariable Long restaurantId,
            @RequestBody MissionReqDTO.CreateMissionDTO createMissionDTO) {

        missionCommandService.createMission(restaurantId, createMissionDTO);
        return ApiResponse.onSuccess("미션이 성공적으로 추가되었습니다.");
    }

    @GetMapping("/{restaurantId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getReviews(
            @ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
            @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Review> reviewList = reviewQueryService.getReviewList(restaurantId, page - 1);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreviewListDTO(reviewList));
    }

    @GetMapping("/{restaurantId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "RESTAURANT404", description = "식당이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE400", description = "유효하지 않은 page 값입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디입니다."),
            @Parameter(name = "page", description = "조회된 목록 페이징에 사용되는 페이지 번호입니다.")
    })
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMissions(
            @ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
            @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Mission> missions = missionQueryService.getByRestaurant(restaurantId, page - 1);
        return ApiResponse.onSuccess(MissionConverter.toMissionPreviewListDTO(missions));
    }
}
