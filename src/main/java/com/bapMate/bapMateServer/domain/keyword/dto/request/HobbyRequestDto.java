package com.bapMate.bapMateServer.domain.keyword.dto.request;

import com.bapMate.bapMateServer.domain.keyword.entity.Hobby;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HobbyRequestDto {

    @NotNull
    private int running;
    @NotNull
    private int climbing;
    @NotNull
    private int tennis;
    @NotNull
    private int hiking;
    @NotNull
    private int activities;

    @NotNull
    private int traveling;
    @NotNull
    private int new_restaurants;
    @NotNull
    private int visiting_hot_spots;
    @NotNull
    private int exchange_student;

    @NotNull
    private int idol;
    @NotNull
    private int anime;
    @NotNull
    private int pets;
    @NotNull
    private int electronic_gadgets;
    @NotNull
    private int collector;

    @NotNull
    private int music;
    @NotNull
    private int exhibitions;
    @NotNull
    private int movies;
    @NotNull
    private int drawing;
    @NotNull
    private int musicals; // 연극 or 뮤지컬

    @NotNull
    private int foreign_languages;
    @NotNull
    private int cooking;
    @NotNull
    private int stocks;
    @NotNull
    private int job_preparation;

    @Builder
    public Hobby toEntity(){
        return Hobby.builder()
                .running(running)
                .climbing(climbing)
                .tennis(tennis)
                .hiking(hiking)
                .activities(activities)
                .traveling(traveling)
                .new_restaurants(new_restaurants)
                .visiting_hot_spots(visiting_hot_spots)
                .exchange_student(exchange_student)
                .idol(idol)
                .anime(anime)
                .pets(pets)
                .electronic_gadgets(electronic_gadgets)
                .collector(collector)
                .music(music)
                .exhibitions(exhibitions)
                .movies(movies)
                .drawing(drawing)
                .musicals(musicals)
                .foreign_languages(foreign_languages)
                .cooking(cooking)
                .stocks(stocks)
                .job_preparation(job_preparation)
                .build();
    }
}
