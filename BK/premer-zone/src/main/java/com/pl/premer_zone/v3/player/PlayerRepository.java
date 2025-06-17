package com.pl.premer_zone.v3.player;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	Optional<Player> findByName(String name);

}
