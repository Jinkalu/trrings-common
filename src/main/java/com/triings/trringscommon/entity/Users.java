package com.triings.trringscommon.entity;

import com.triings.trringscommon.enums.*;
import com.triings.trringscommon.utils.AbstractAuditEntity;
import jakarta.persistence.*;

import java.util.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static jakarta.persistence.EnumType.ORDINAL;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users extends AbstractAuditEntity implements UserDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,updatable = false)
    private UUID userUid;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String username;

    @Enumerated(ORDINAL)
    private AccountType accountType;

    @Column(nullable = false)
    private String email;

    @Column
    private String mobile;

    @Column
    private String password;

    @Column
    @Enumerated(ORDINAL)
    private Gender gender;


    private Long dob;

    private String bio;

    private String profilePicture;

    private String coverPicture;

    private Long cityId;

    private Boolean isVerified;

    private String location;

    @Column(nullable = false)
    @Enumerated(ORDINAL)
    private UserStatus status;

    private Boolean isSocialAccount;

    @Enumerated(ORDINAL)
    private SocialLoginType socialType;

    private Long passwordUpdatedAt;

    private Long lastSignInAt;

    private String address;

    private String website;

    private String customBadge;

    @Enumerated(ORDINAL)
    private VerificationType verificationType;

    private Boolean isPrivate;

    private String socialLoginPassword;

    private String platformPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_id")
    private Role userRole;

    @ManyToMany
    @JoinTable(
            name = "UserPermission",
            joinColumns = @JoinColumn(name = "usersId"),
            inverseJoinColumns = @JoinColumn(name = "permissionId")
    )
    private Set<Permission> permissions;

    @OneToMany(mappedBy = "user")
    private Set<Token> tokens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    public String getUserUniqueName() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
