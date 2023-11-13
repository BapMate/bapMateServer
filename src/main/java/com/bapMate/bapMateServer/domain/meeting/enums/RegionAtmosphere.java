package com.bapMate.bapMateServer.domain.meeting.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RegionAtmosphere {
    INSTA("#인스타감성",0),
    NEW_HOT_PLACE("#신상핫플",0);
    private final String title;
    private final int check;

    public String getTitle() {return title;}
    public int getCheck() {return check;}
}
