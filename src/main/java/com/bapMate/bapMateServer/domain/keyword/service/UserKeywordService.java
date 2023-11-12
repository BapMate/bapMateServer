package com.bapMate.bapMateServer.domain.keyword.service;

import com.bapMate.bapMateServer.domain.keyword.dto.request.EatingRequestDto;
import com.bapMate.bapMateServer.domain.keyword.dto.request.HobbyRequestDto;
import com.bapMate.bapMateServer.domain.keyword.dto.request.PersonalityRequestDto;
import com.bapMate.bapMateServer.domain.keyword.entity.Eating;
import com.bapMate.bapMateServer.domain.keyword.entity.Hobby;
import com.bapMate.bapMateServer.domain.keyword.entity.Personality;
import com.bapMate.bapMateServer.domain.keyword.repository.EatingRepository;
import com.bapMate.bapMateServer.domain.keyword.repository.HobbyRepository;
import com.bapMate.bapMateServer.domain.keyword.repository.PersonalityRepository;
import com.bapMate.bapMateServer.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Transactional
@Service
public class UserKeywordService {

    private final HobbyRepository hobbyRepository;
    private final PersonalityRepository personalityRepository;
    private final EatingRepository eatingRepository;

    public void setUserHobby(User user, HobbyRequestDto requestDto){
        Hobby hobby = requestDto.toEntity();

        if(user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST); // 수정 예정
        hobby.setUser(user);

        hobbyRepository.save(hobby);
    }

    public void setUserPersonality(User user, PersonalityRequestDto requestDto){
        Personality personality = requestDto.toEntity();

        if(user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST); // 수정 예정
        personality.setUser(user);

        personalityRepository.save(personality);
    }

    public void setUserEating(User user, EatingRequestDto requestDto){
        Eating eating = requestDto.toEntity();

        if(user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST); // 수정 예정
        eating.setUser(user);

        eatingRepository.save(eating);
    }
}
