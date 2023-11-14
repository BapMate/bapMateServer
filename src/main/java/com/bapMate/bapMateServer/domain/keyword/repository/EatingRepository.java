package com.bapMate.bapMateServer.domain.keyword.repository;

import com.bapMate.bapMateServer.domain.keyword.entity.Eating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EatingRepository extends JpaRepository<Eating, Long> {
}
