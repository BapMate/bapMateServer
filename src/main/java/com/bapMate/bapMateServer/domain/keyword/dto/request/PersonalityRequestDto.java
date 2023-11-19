package com.bapMate.bapMateServer.domain.keyword.dto.request;

import com.bapMate.bapMateServer.domain.keyword.entity.Personality;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonalityRequestDto {
    @NotNull
    private int humorous;
    @NotNull
    private int lively;
    @NotNull
    private int talkative;
    @NotNull
    private int high_energy;

    @NotNull
    private int constructive;
    @NotNull
    private int self_improving;
    @NotNull
    private int passionate;
    @NotNull
    private int ambitious;

    @NotNull
    private int empathetic;
    @NotNull
    private int sensible;
    @NotNull
    private int approachable;
    @NotNull
    private int good_listener;

    @NotNull
    private int shy;
    @NotNull
    private int reserved;
    @NotNull
    private int quiet;
    @NotNull
    private int timid;

    @NotNull
    private int spontaneous;
    @NotNull
    private int adventurous;
    @NotNull
    private int creative;
    @NotNull
    private int good_under_pressure;

    @Builder
    public Personality toEntity(){
        return Personality.builder()
                .humorous(humorous)
                .lively(lively)
                .talkative(talkative)
                .high_energy(high_energy)
                .constructive(constructive)
                .self_improving(self_improving)
                .passionate(passionate)
                .ambitious(ambitious)
                .empathetic(empathetic)
                .sensible(sensible)
                .approachable(approachable)
                .good_listener(good_listener)
                .shy(shy)
                .reserved(reserved)
                .quiet(quiet)
                .timid(timid)
                .spontaneous(spontaneous)
                .adventurous(adventurous)
                .creative(creative)
                .good_under_pressure(good_under_pressure)
                .build();
    }
}
