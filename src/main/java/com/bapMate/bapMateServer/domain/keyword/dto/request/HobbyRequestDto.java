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
    private Boolean running;
    @NotNull
    private Boolean climbing;
    @NotNull
    private Boolean tennis;
    @NotNull
    private Boolean hiking;
    @NotNull
    private Boolean activities;

    @NotNull
    private Boolean traveling;
    @NotNull
    private Boolean new_restaurants;
    @NotNull
    private Boolean visiting_hot_spots;
    @NotNull
    private Boolean exchange_student;

    @NotNull
    private Boolean idol;
    @NotNull
    private Boolean anime;
    @NotNull
    private Boolean pets;
    @NotNull
    private Boolean electronic_gadgets;
    @NotNull
    private Boolean collector;

    @NotNull
    private Boolean music;
    @NotNull
    private Boolean exhibitions;
    @NotNull
    private Boolean movies;
    @NotNull
    private Boolean drawing;
    @NotNull
    private Boolean musicals; // 연극 or 뮤지컬

    @NotNull
    private Boolean foreign_languages;
    @NotNull
    private Boolean cooking;
    @NotNull
    private Boolean stocks;
    @NotNull
    private Boolean job_preparation;

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
