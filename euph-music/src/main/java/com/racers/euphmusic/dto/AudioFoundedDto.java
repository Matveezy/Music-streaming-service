package com.racers.euphmusic.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AudioFoundedDto {

    private Integer id;

    private String name;

    private String image;

    List<PersonShowProfileDto> authors;
}
