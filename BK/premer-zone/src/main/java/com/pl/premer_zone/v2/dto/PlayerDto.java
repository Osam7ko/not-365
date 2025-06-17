package com.pl.premer_zone.v2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private String name;
    private String position;
    private int age;
    private int goals;
    private int assists;
    private int minutes;
    private String team;
}
