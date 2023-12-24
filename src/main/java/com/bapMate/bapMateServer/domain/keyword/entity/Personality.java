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


    private int humorous;
    private int lively;
    private int talkative;
    private int high_energy;

    private int constructive;
    private int self_improving;
    private int passionate;
    private int ambitious;

    private int empathetic;
    private int sensible;
    private int approachable;
    private int good_listener;

    private int shy;
    private int reserved;
    private int quiet;
    private int timid;

    private int spontaneous;
    private int adventurous;
    private int creative;
    private int good_under_pressure;
}
