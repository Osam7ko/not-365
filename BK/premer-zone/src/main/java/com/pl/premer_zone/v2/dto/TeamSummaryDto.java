package com.pl.premer_zone.v2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamSummaryDto {
    private String teamName;
    private String logo;
    private String topScorer;
    private int totalGoals;
}
