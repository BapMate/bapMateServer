package com.bapMate.bapMateServer.domain.participation.repository;

import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    List<Participation> findAllByUserId(Long id);
}
