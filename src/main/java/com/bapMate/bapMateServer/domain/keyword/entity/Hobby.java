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
    @Column(name = "hobby_id")
    private Long id;

    @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int running;
    private int climbing;
    private int tennis;
    private int hiking;
    private int activities;

    private int traveling;
    private int new_restaurants;
    private int visiting_hot_spots;
    private int exchange_student;

    private int idol;
    private int anime;
    private int pets;
    private int electronic_gadgets;
    private int collector;

    private int music;
    private int exhibitions;
    private int movies;
    private int drawing;
    private int musicals;

    private int foreign_languages;
    private int cooking;
    private int stocks;
    private int job_preparation;

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
                setHobbyField(this, hobbyType.name().toLowerCase(), 1);
            }
        }
    }

    private void initializeHobbies() {
        this.running = 0;
        this.climbing = 0;
        this.tennis = 0;
        this.hiking = 0;
        this.activities = 0;

        this.traveling = 0;
        this.new_restaurants = 0;
        this.visiting_hot_spots = 0;
        this.exchange_student = 0;

        this.idol = 0;
        this.anime = 0;
        this.pets = 0;
        this.electronic_gadgets = 0;
        this.collector = 0;

        this.music = 0;
        this.exhibitions = 0;
        this.movies = 0;
        this.drawing = 0;
        this.musicals = 0;

        this.foreign_languages = 0;
        this.cooking = 0;
        this.stocks = 0;
        this.job_preparation = 0;
    }

    private void setHobbyField(Hobby hobby, String fieldName, int value) {
        try {
            Field field = hobby.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(hobby, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
