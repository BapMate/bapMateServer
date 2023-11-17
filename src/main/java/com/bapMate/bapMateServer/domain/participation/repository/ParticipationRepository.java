package com.bapMate.bapMateServer.domain.participation.repository;

import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    List<Participation> findAllByUserId(Long id);

    //Participation findByMeetUpId(Long meetUpId);
}
