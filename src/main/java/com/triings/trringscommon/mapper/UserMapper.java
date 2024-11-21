package com.triings.trringscommon.mapper;

import com.triings.trringscommon.entity.Users;
import com.triings.trringscommon.vo.UserVO;
import org.apache.commons.lang3.StringUtils;

public class UserMapper {

    public static UserVO mapToUserVO(Users user) {
        return UserVO.builder()
                .id(user.getId())
                .userUid(user.getUserUid().toString())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .gender(user.getGender().name())
                .isVerified(user.getIsVerified())
                .bio(StringUtils.isEmpty(user.getBio()) ? user.getBio() : null)
                .status((short) user.getStatus().ordinal())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
