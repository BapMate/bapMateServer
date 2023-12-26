package com.bapMate.bapMateServer.domain.meeting.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RegionAtmosphere {
    INSTA("#인스타감성",1),
    NEW_HOT_PLACE("#신상핫플",1);
    private final String title;
    private final int check;

    public String getTitle() {return title;}
    public int getCheck() {return check;}

    // String 값을 Enum으로 변환하는 메서드
    public static RegionAtmosphere fromTitle(String title) {
        System.out.println(title);
        for (RegionAtmosphere atmosphere : RegionAtmosphere.values()) {
            if (atmosphere.title.equals(title)) {
                System.out.println(atmosphere);
                return atmosphere;
            }
        }
        throw new IllegalArgumentException("Unknown title: " + title);
    }
}
