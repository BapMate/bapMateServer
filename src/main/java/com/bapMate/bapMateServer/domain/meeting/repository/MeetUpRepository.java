package com.bapMate.bapMateServer.domain.meeting.repository;

import com.bapMate.bapMateServer.domain.meeting.entity.MeetUp;
import com.bapMate.bapMateServer.domain.meeting.enums.MeetUpAtmosphere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MeetUpRepository extends JpaRepository<MeetUp, Long> {
    @Modifying
    @Query("UPDATE MeetUp m SET m.currentNumberOfPeople = m.currentNumberOfPeople + 1 WHERE m.id = :meetUpId")
    void incrementCurrentNumberOfPeople(@Param("meetUpId") Long meetUpId);

    List<MeetUp> findByMeetUpAtmosphere(MeetUpAtmosphere meetUpAtmoshere);
}
