package com.bapMate.bapMateServer.domain.keyword.repository;

import com.bapMate.bapMateServer.domain.keyword.entity.Hobby;
import com.bapMate.bapMateServer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {

    Hobby findByUser(User user);
}
