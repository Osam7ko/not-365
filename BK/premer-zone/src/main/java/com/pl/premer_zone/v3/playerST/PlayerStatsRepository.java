package com.pl.premer_zone.v3.playerST;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pl.premer_zone.v3.player.Player;
import com.pl.premer_zone.v3.season.Season;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

	Optional<PlayerStats> findFirstByPlayerAndSeason(Player player, Season season);

	Optional<PlayerStats> findTopByPlayerOrderBySeason_YearDesc(Player player);

	List<PlayerStats> findTop10ByOrderByGoalsDesc();

	List<PlayerStats> findAllByOrderByGoalsDesc();

	@Query("SELECT p FROM PlayerStats p WHERE p.season.id = :seasonId ORDER BY p.goals DESC")
	List<PlayerStats> findTopScorersBySeason(@Param("seasonId") Long seasonId, Pageable pageable);

	@Query("SELECT p FROM PlayerStats p WHERE p.season.id = :seasonId ORDER BY p.assists DESC")
	List<PlayerStats> findTopAssistersBySeason(@Param("seasonId") Long seasonId, Pageable pageable);

}
