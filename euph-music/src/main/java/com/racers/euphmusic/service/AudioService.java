package com.racers.euphmusic.service;

import com.racers.euphmusic.dto.AudioReadDto;
import com.racers.euphmusic.mapper.AudioReadMapper;
import com.racers.euphmusic.repository.AudioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AudioService {

    private final AudioRepo audioRepo;
    private final AudioReadMapper audioReadMapper;

    public List<AudioReadDto> findAll() {
        return audioRepo.findAll()
                .stream()
                .map(audioReadMapper::map)
                .toList();
    }

    public Optional<AudioReadDto> findById(Integer id) {
        return audioRepo.findById(id)
                .map(audioReadMapper::map);
    }


}
