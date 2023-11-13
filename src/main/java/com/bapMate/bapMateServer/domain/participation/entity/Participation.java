package com.bapMate.bapMateServer.domain.participation.entity;

import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpStatus;
import com.bapMate.bapMateServer.domain.user.entity.User;
import com.bapMate.bapMateServer.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Participation extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "participation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meet_up_id")
    private MeetUp meetUp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private MeetUpStatus meetUpStatus;
}

