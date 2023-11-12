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
    private Boolean humorous;
    @NotNull
    private Boolean lively;
    @NotNull
    private Boolean talkative;
    @NotNull
    private Boolean high_energy;

    @NotNull
    private Boolean constructive;
    @NotNull
    private Boolean self_improving;
    @NotNull
    private Boolean passionate;
    @NotNull
    private Boolean ambitious;

    @NotNull
    private Boolean empathetic;
    @NotNull
    private Boolean sensible;
    @NotNull
    private Boolean approachable;
    @NotNull
    private Boolean good_listener;

    @NotNull
    private Boolean shy;
    @NotNull
    private Boolean reserved;
    @NotNull
    private Boolean quiet;
    @NotNull
    private Boolean timid;

    @NotNull
    private Boolean spontaneous;
    @NotNull
    private Boolean adventurous;
    @NotNull
    private Boolean creative;
    @NotNull
    private Boolean good_under_pressure;

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
