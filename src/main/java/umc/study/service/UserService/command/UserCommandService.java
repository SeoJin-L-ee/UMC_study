package umc.study.service.UserService.command;

import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.web.dto.ChallengeValidationDTO;
import umc.study.web.dto.reviewDTO.ReviewReqDTO;
import umc.study.web.dto.userDTO.UserReqDTO;

public interface UserCommandService {

    User joinUser(UserReqDTO.CreateUserReqDTO createUserReqDTO);

    Review createReview(Long userId, Long restaurantId, ReviewReqDTO.CreateReviewReqDTO createReviewReqDTO);

    void challengeMission(ChallengeValidationDTO dto);
}
