package umc.study.service.UserService.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodTypeHandler;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.apiPayload.exception.handler.UserHandler;
import umc.study.converter.ReviewConverter;
import umc.study.converter.UserConverter;
import umc.study.converter.UserTasteConverter;
import umc.study.domain.*;
import umc.study.domain.enums.FoodType;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.MissionRepository;
import umc.study.repository.RestaurantRepository2;
import umc.study.repository.ReviewRepository;
import umc.study.repository.UserRepository;
import umc.study.web.dto.ChallengeValidationDTO;
import umc.study.web.dto.reviewDTO.ReviewReqDTO;
import umc.study.web.dto.userDTO.UserReqDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RestaurantRepository2 restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User joinUser(UserReqDTO.CreateUserReqDTO createUserReqDTO) {

        User user = UserConverter.toUser(createUserReqDTO);
        user.encodePassword(passwordEncoder.encode(createUserReqDTO.getPassword()));
        log.info("여까진 정상작동합니다!");

        List<FoodType> foodTypeList = createUserReqDTO.getPreferCategory().stream()
                .map(category -> {
                    try {
                        return FoodType.fromDescription(category);
                    } catch (IllegalArgumentException e) {
                        log.info("그런 음식 유형 없습니다.");
                        throw new FoodTypeHandler(ErrorStatus.FOOD_TYPE_NOT_FOUND);
                    }
                })
                .toList();

        List<UserTaste> userTasteList = UserTasteConverter.toUserTasteList(foodTypeList);
        userTasteList.forEach(userTaste -> {userTaste.setUser(user);});

        return userRepository.save(user);
    }

    @Override
    public Review createReview(Long userId, Long restaurantId, ReviewReqDTO.CreateReviewReqDTO createReviewReqDTO) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Review review = ReviewConverter.toReview(createReviewReqDTO);
        review.setUser(user);
        review.setRestaurant(restaurant);

        return reviewRepository.save(review);
    }

    @Override
    public void challengeMission(ChallengeValidationDTO dto) {

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        Mission mission = missionRepository.findById(dto.getMissionId()).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        UserMission userMission = UserMission.builder().build();
        userMission.setUser(user);
        userMission.setMission(mission);

        mission.getUserMissionList().add(userMission);
        user.getUserMissionList().add(userMission);
    }
}
