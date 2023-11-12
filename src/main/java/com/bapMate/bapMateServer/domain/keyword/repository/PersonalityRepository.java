package com.bapMate.bapMateServer.domain.keyword.repository;

import com.bapMate.bapMateServer.domain.keyword.entity.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityRepository extends JpaRepository<Personality, Long> {
}
