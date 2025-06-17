package com.pl.premer_zone.v3.team;

import java.util.Set;

import com.pl.premer_zone.v3.season.Season;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	private String logoUrl;

	@ManyToMany
	@JoinTable(name = "team_season", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "season_id"))
	private Set<Season> seasons;
}
