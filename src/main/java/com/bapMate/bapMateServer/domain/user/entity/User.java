package com.bapMate.bapMateServer.domain.user.entity;

import com.bapMate.bapMateServer.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "loginType"})})
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Nullable
    private String name;
    @Embedded
    private AuthInfo authInfo;
    @Nullable
    private String universityName;

    @Builder.Default
    private Boolean universityIsAuthenticated = false;

    public void authenticateUniversity() {
        this.universityIsAuthenticated = true;
    }

}