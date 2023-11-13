package com.bapMate.bapMateServer.domain.meeting.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MeetUpAtmosphere {
    QUIET("#조용한식사",0),
    ENFP("#극E",0),
    FOOD_FIGHTER("#맛집탐방",0),
    SMALL_EATER("#자기계발",0),
    //굳이 이름이 필요할까? client에서 조용한 식사를 선택하면 0을 1로
    HOBBIES("취미를 공유하고 싶어요",0);

    private final String title;
    private final int check;

    public String getTitle() {return title;}
    public int getCheck() {return check;}
}
