package com.bapMate.bapMateServer.domain.meeting.repository;

import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetUpRepository extends JpaRepository<MeetUp, Long> {
}
