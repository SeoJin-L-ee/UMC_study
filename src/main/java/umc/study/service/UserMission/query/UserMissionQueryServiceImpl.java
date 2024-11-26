package umc.study.service.UserMission.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.UserHandler;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.UserMissionRepository;
import umc.study.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryServiceImpl implements UserMissionQueryService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    private static final int PAGE_SIZE = 10;

    @Override
    public Page<UserMission> getOngoingByUserId(Long userId, Integer page) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        return userMissionRepository.findByUserAndIsDoneIsFalse(user, PageRequest.of(page, PAGE_SIZE));
    }
}
