package com.bapMate.bapMateServer.domain.keyword.dto.request;

import com.bapMate.bapMateServer.domain.keyword.entity.Amount;
import com.bapMate.bapMateServer.domain.keyword.entity.Eating;
import com.bapMate.bapMateServer.domain.keyword.entity.Pace;
import com.bapMate.bapMateServer.global.validator.Enum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EatingRequestDto {
    @Enum(enumClass = Amount.class, ignoreCase = true)
    private String amount;

    @Enum(enumClass = Pace.class, ignoreCase = true)
    private String pace;

    @Builder
    public Eating toEntity(){
        return Eating.builder()
                .amount(Amount.valueOf(amount))
                .pace(Pace.valueOf(pace))
                .build();
    }
    /**
     * Eating 서비스 컨트롤러 구현!
     */

}
