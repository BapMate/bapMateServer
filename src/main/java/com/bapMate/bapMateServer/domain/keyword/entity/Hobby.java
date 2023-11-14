package com.bapMate.bapMateServer.domain.keyword.entity;

import com.bapMate.bapMateServer.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder // 엔티티에 builder 붙이는게 최선?
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean running;
    private Boolean climbing;
    private Boolean tennis;
    private Boolean hiking;
    private Boolean activities;

    private Boolean traveling;
    private Boolean new_restaurants;
    private Boolean visiting_hot_spots;
    private Boolean exchange_student;

    private Boolean idol;
    private Boolean anime;
    private Boolean pets;
    private Boolean electronic_gadgets;
    private Boolean collector;

    private Boolean music;
    private Boolean exhibitions;
    private Boolean movies;
    private Boolean drawing;
    private Boolean musicals; // 연극 or 뮤지컬

    private Boolean foreign_languages;
    private Boolean cooking;
    private Boolean stocks;
    private Boolean job_preparation;

}
