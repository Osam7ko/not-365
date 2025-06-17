package com.pl.premer_zone.v3.leagueBP;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pl.premer_zone.v3.season.Season;

public interface LeagueBestPlayerRepository extends JpaRepository<LeagueBestPlayer, Long> {

	void deleteBySeason(Season season);

	List<LeagueBestPlayer> findBySeason(Season season);

}
