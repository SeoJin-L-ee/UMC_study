package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.converter.UserConverter;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.service.ReviewService.query.ReviewQueryService;
import umc.study.service.UserMission.query.UserMissionQueryService;
import umc.study.service.UserService.command.UserCommandService;
import umc.study.validation.annotation.ChallengedMission;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistRestaurant;
import umc.study.web.dto.ChallengeValidationDTO;
import umc.study.web.dto.missionDTO.MissionResDTO;
import umc.study.web.dto.reviewDTO.ReviewReqDTO;
import umc.study.web.dto.reviewDTO.ReviewResDTO;
import umc.study.web.dto.userDTO.UserReqDTO;
import umc.study.web.dto.userDTO.UserResDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserCommandService userCommandService;
    private final ReviewQueryService reviewQueryService;
    private final UserMissionQueryService userMissionQueryService;

    @PostMapping("/")
    @Operation(summary = "회원가입(간략화) API")
    public ApiResponse<UserResDTO.CreateUserResDTO> joinUser(@RequestBody @Valid UserReqDTO.CreateUserReqDTO createUserReqDTO) {

        User user = userCommandService.joinUser(createUserReqDTO);
        return ApiResponse.onSuccess(UserConverter.toCreateUserResDTO(user));
    }

    @PostMapping("/{userId}/restaurants/{restaurantId}/reviews")
    @Operation(summary = "가게에 리뷰 추가하기 API")
    public ApiResponse<ReviewResDTO.CreateReviewResDTO> createReview(
            @PathVariable Long userId,
            @ExistRestaurant @PathVariable Long restaurantId,
            @Valid @RequestBody ReviewReqDTO.CreateReviewReqDTO createReviewReqDTO) {

        Review review = userCommandService.createReview(userId, restaurantId, createReviewReqDTO);
        return ApiResponse.onSuccess(ReviewConverter.toCreateReviewResDTO(review));
    }

    @PostMapping("/missions/do")
    @Operation(summary = "특정 유저가 특정 가게의 미션을 도전 중인 미션으로 추가하는 API")
    public ApiResponse<String> challengeMission(@Valid @ChallengedMission @RequestBody ChallengeValidationDTO challengeValidationDTO) {

        userCommandService.challengeMission(challengeValidationDTO);
        return ApiResponse.onSuccess("미션에 도전하였습니다!");
    }

    @GetMapping("/{userId}/reviews")
    @Operation(summary = "특정 유저가 작성한 리뷰 목록 조회 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "RESTAURANT404", description = "식당이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE400", description = "유효하지 않은 page 값입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디입니다."),
            @Parameter(name = "page", description = "조회된 목록 페이징에 사용되는 페이지 번호입니다.")
    })
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getUserReviews(
            @PathVariable(name = "userId") Long userId,
            @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Review> userReviews = reviewQueryService.getByUserId(userId, page - 1);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreviewListDTO(userReviews));
    }

    @GetMapping("/{userId}/missions")
    @Operation(summary = "특정 유저가 진행 중인 미션 목록 조회 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER400", description = "사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE400", description = "유효하지 않은 page 값입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디입니다."),
            @Parameter(name = "page", description = "조회된 목록 페이징에 사용되는 페이지 번호입니다.")
    })
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getUserMissions(
            @PathVariable(name = "userId") Long userId,
            @CheckPage @RequestParam(name = "page") Integer page) {

        Page<UserMission> userMissions = userMissionQueryService.getOngoingByUserId(userId, page - 1);
        return ApiResponse.onSuccess(MissionConverter.toUserMissionPreviewListDTO(userMissions));
    }
}
