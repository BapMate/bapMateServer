package com.bapMate.bapMateServer.domain.participation.repository;

import com.bapMate.bapMateServer.domain.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

}
