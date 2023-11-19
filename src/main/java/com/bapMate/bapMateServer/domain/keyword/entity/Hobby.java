package com.bapMate.bapMateServer.domain.keyword.entity;

import com.bapMate.bapMateServer.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Field;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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
    private Boolean musicals;

    private Boolean foreign_languages;
    private Boolean cooking;
    private Boolean stocks;
    private Boolean job_preparation;

    /**
     * String 리스트로 받아와서 존재하는 필드명을 true로 바꿔주게끔
     * 1차 refactoring
     */
    // 새로운 생성자
    public Hobby(User user, List<String> hobbyDescriptions) {
        this.user = user;

        initializeHobbies(); // 모든 취미 필드를 false로 초기화

        // DTO에서 받은 취미 설명으로 필드 설정
        for (String description : hobbyDescriptions) {
            HobbyType hobbyType = HobbyType.fromDescription(description);
            if (hobbyType != null) {
                setHobbyField(this, hobbyType.name().toLowerCase(), true);
            }
        }
    }

    private void initializeHobbies() {
        this.running = false;
        this.climbing = false;
        this.tennis = false;
        this.hiking = false;
        this.activities = false;

        this.traveling = false;
        this.new_restaurants = false;
        this.visiting_hot_spots = false;
        this.exchange_student = false;

        this.idol = false;
        this.anime = false;
        this.pets = false;
        this.electronic_gadgets = false;
        this.collector = false;

        this.music = false;
        this.exhibitions = false;
        this.movies = false;
        this.drawing = false;
        this.musicals = false;

        this.foreign_languages = false;
        this.cooking = false;
        this.stocks = false;
        this.job_preparation = false;
    }

    private void setHobbyField(Hobby hobby, String fieldName, boolean value) {
        try {
            Field field = hobby.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(hobby, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
