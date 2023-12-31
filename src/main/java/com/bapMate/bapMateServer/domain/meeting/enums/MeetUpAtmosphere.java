package com.bapMate.bapMateServer.domain.meeting.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MeetUpAtmosphere {
    QUIET("#조용한식사",1),
    ENFP("#극E",1),
    FOOD_FIGHTER("#맛집탐방",1),
    SMALL_EATER("#소식",1),
    //굳이 이름이 필요할까? client에서 조용한 식사를 선택하면 0을 1로
    HOBBIES("#취미공유",1),
    LOUD("#왁자지껄",1);


    private final String title;
    private final int check;

    public String getTitle() {return title;}
    public int getCheck() {return check;}

    public static MeetUpAtmosphere fromTitle(String title) {
        for (MeetUpAtmosphere atmosphere : MeetUpAtmosphere.values()) {
            if (atmosphere.title.equals(title)) {
                return atmosphere;
            }
        }
        return null; // 매칭되는 값이 없을 경우
    }
}
