package umc.study.service.UserService.command;

import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.web.dto.ChallengeValidationDTO;
import umc.study.web.dto.reviewDTO.ReviewRequestDTO;
import umc.study.web.dto.userDTO.UserRequestDTO;

public interface UserCommandService {

    User joinUser(UserRequestDTO.CreateUserReqDTO createUserReqDTO);

    Review createReview(Long userId, Long restaurantId, ReviewRequestDTO.CreateReviewReqDTO createReviewReqDTO);

    void challengeMission(ChallengeValidationDTO dto);
}
