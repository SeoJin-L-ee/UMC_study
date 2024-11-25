package umc.study.converter;

import umc.study.domain.User;
import umc.study.domain.enums.MemberStatus;
import umc.study.web.dto.userDTO.UserRequestDTO;
import umc.study.web.dto.userDTO.UserResponseDTO;

import java.util.ArrayList;

public class UserConverter {

    public static User toUser(UserRequestDTO.CreateUserReqDTO createUserReqDTO) {

        return User.builder()
                .name(createUserReqDTO.getName())
                .gender(createUserReqDTO.getGender())
                .address(createUserReqDTO.getAddress())
                .email(createUserReqDTO.getEmail())
                .point(0)
                .memberStatus(MemberStatus.ACTIVE)
                .userTasteList(new ArrayList<>())
                .build();
    }

    public static UserResponseDTO.CreateUserResDTO toCreateUserResDTO(User user) {

        return UserResponseDTO.CreateUserResDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
