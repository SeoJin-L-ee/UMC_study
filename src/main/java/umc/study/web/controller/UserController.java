package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.converter.UserConverter;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.service.UserService.command.UserCommandService;
import umc.study.service.UserService.query.UserQueryService;
import umc.study.validation.annotation.ChallengedMission;
import umc.study.validation.annotation.ExistRestaurant;
import umc.study.web.dto.ChallengeValidationDTO;
import umc.study.web.dto.reviewDTO.ReviewRequestDTO;
import umc.study.web.dto.reviewDTO.ReviewResponseDTO;
import umc.study.web.dto.userDTO.UserRequestDTO;
import umc.study.web.dto.userDTO.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @PostMapping("/")
    @Operation(summary = "회원가입(간략화) API")
    public ApiResponse<UserResponseDTO.CreateUserResDTO> joinUser(@RequestBody @Valid UserRequestDTO.CreateUserReqDTO createUserReqDTO) {

        User user = userCommandService.joinUser(createUserReqDTO);
        return ApiResponse.onSuccess(UserConverter.toCreateUserResDTO(user));
    }

    @PostMapping("/{userId}/restaurants/{restaurantId}/reviews")
    @Operation(summary = "가게에 리뷰 추가하기 API")
    public ApiResponse<ReviewResponseDTO.CreateReviewResDTO> createReview(
            @PathVariable Long userId,
            @ExistRestaurant @PathVariable Long restaurantId,
            @Valid @RequestBody ReviewRequestDTO.CreateReviewReqDTO createReviewReqDTO) {

        Review review = userCommandService.createReview(userId, restaurantId, createReviewReqDTO);
        return ApiResponse.onSuccess(ReviewConverter.toCreateReviewResDTO(review));
    }

    @PostMapping("/missions/do")
    @Operation(summary = "특정 유저가 특정 가게의 미션을 도전 중인 미션으로 추가하는 API")
    public ApiResponse<String> challengeMission(@Valid @ChallengedMission @RequestBody ChallengeValidationDTO challengeValidationDTO) {

        userCommandService.challengeMission(challengeValidationDTO);
        return ApiResponse.onSuccess("미션에 도전하였습니다!");
    }
}
