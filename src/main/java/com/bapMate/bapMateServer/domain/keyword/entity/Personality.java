package com.bapMate.bapMateServer.domain.keyword.entity;

import com.bapMate.bapMateServer.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Personality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personality_id")
    private Long id;

    @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    private Boolean humorous;
    private Boolean lively;
    private Boolean talkative;
    private Boolean high_energy;

    private Boolean constructive;
    private Boolean self_improving;
    private Boolean passionate;
    private Boolean ambitious;

    private Boolean empathetic;
    private Boolean sensible;
    private Boolean approachable;
    private Boolean good_listener;

    private Boolean shy;
    private Boolean reserved;
    private Boolean quiet;
    private Boolean timid;

    private Boolean spontaneous;
    private Boolean adventurous;
    private Boolean creative;
    private Boolean good_under_pressure;
}
