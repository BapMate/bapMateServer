package com.bapMate.bapMateServer.domain.keyword.repository;

import com.bapMate.bapMateServer.domain.keyword.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
