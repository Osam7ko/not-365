package com.pl.premer_zone.v3.leagueTS;

import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.team.Team;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "league_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeagueTableStanding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "season_id", nullable = false)
	private Season season;

	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
	private Team team;

	private int position;

	private int points;
}
