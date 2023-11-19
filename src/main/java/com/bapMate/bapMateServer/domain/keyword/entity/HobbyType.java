package com.bapMate.bapMateServer.domain.keyword.entity;

public enum HobbyType {
    RUNNING("#러닝"),
    CLIMBING("#클라이밍"),
    TENNIS("#테니스"),
    HIKING("#등산"),
    ACTIVITIES("#액티비티"),
    TRAVELING("#여행"),
    NEW_RESTAURANTS("#맛집탐방"),
    VISITING_HOT_SPOTS("#핫플레이스탐방"),
    EXCHANGE_STUDENT("#교환학생"),
    IDOL("#아이돌"),
    ANIME("#애니"),
    PETS("#애완동물"),
    ELECTRONIC_GADGETS("#전자기기"),
    COLLECTOR("#컬렉터"),
    MUSIC("#음악듣기"),
    EXHIBITIONS("#전시회"),
    MOVIES("#영화보기"),
    DRAWING("#그림그리기"),
    MUSICALS("#뮤지컬"),
    FOREIGN_LANGUAGES("#외국어"),
    COOKING("#요리"),
    STOCKS("#주식"),
    JOB_PREPARATION("#취업준비");

    private final String description;

    HobbyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // 문자열 값을 받아 해당하는 HobbyType 반환
    public static HobbyType fromDescription(String description) {
        for (HobbyType hobbyType : HobbyType.values()) {
            if (hobbyType.getDescription().equals(description)) {
                return hobbyType;
            }
        }
        return null; // 예외 처리로 수정 예정
    }

}
