package com.triings.trringscommon.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2118792558328451302L;

    private Long id;
    private String userUid;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String profilePicture;
    private String coverImage;
    private String accountType;
    private Integer countryId;
    private short status;
    private Boolean isVerified;
    private String customBadge;
    private String verificationType;
    private Boolean isLiked;
    private Boolean isFollowing;
    private Boolean isBlocked;
    private Boolean isPrivate;
    private Boolean isViewed;
    private Boolean isMuted;
    private String gender;
    private String bio;
    private Long dob;
    private Long createdAt;
    private String address;
    private String website;
    private Long followingCount;
    private Long followerCount;
    private Long associateCount;
    private Long requestCount;
    private Long postCount;
    private Long momentsCount;
    private Long likedPostCount;
    private Long replyCount;
    private Long mentionCount;
    private Long highLightsCount;
    private String userRole;
    private Long totalMediaCount;
    private UserVO companyDetails;
}
