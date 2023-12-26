package com.bapMate.bapMateServer.domain.univcert.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnivRequestDto {
    private String univName;
    private String univEmail;
}
