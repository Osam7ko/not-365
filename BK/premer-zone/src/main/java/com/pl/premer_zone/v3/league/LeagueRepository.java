package com.pl.premer_zone.v3.league;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {

	Optional<League> findByName(String name);

}
