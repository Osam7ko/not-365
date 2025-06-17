package com.pl.premer_zone.v3.playerST;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v3/player-stats")
@RequiredArgsConstructor
public class PlayerStatsController {

	private final PlayerStatsService playerStatsService;

	@Operation(summary = "Create new stats for a player")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Player stats created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request") })
	@PostMapping
	public ResponseEntity<PlayerStatsDto> create(@RequestBody PlayerStatsDto dto) {
		return ResponseEntity.ok(playerStatsService.createPlayerStats(dto));
	}

	@Operation(summary = "Get all player stats")
	@ApiResponse(responseCode = "200", description = "All stats retrieved")
	@GetMapping
	public ResponseEntity<List<PlayerStatsDto>> getAll() {
		return ResponseEntity.ok(playerStatsService.getAllStats());
	}

	@Operation(summary = "Get stats by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Stats found"),
			@ApiResponse(responseCode = "404", description = "Stats not found") })
	@GetMapping("/{id}")
	public ResponseEntity<PlayerStatsDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(playerStatsService.getById(id));
	}

	@Operation(summary = "Get stats for a specific season")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Stats for the season found"),
			@ApiResponse(responseCode = "404", description = "Season not found") })
	@GetMapping("/season/{seasonId}")
	public ResponseEntity<List<PlayerStatsDto>> getBySeason(@PathVariable Long seasonId) {
		return ResponseEntity.ok(playerStatsService.getStatsBySeason(seasonId));
	}

	@Operation(summary = "Get stats for a specific player")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Stats for the player found"),
			@ApiResponse(responseCode = "404", description = "Player not found") })
	@GetMapping("/player/{playerId}")
	public ResponseEntity<List<PlayerStatsDto>> getByPlayer(@PathVariable Long playerId) {
		return ResponseEntity.ok(playerStatsService.getStatsByPlayer(playerId));
	}

	@Operation(summary = "Get latest stats for a specific player")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Latest stats found"),
			@ApiResponse(responseCode = "404", description = "Stats not found") })
	@GetMapping("/player/{playerId}/latest")
	public ResponseEntity<PlayerStatsDto> getLatestForPlayer(@PathVariable Long playerId) {
		return ResponseEntity.ok(playerStatsService.getLatestStatsForPlayer(playerId));
	}

	@Operation(summary = "Get top scorers")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Top scorers retrieved successfully") })
	@GetMapping("/top-scorers")
	public ResponseEntity<List<PlayerStatsDto>> getTopScorers(@RequestParam(defaultValue = "10") int limit) {
		return ResponseEntity.ok(playerStatsService.getTopScorers(limit));
	}

	@Operation(summary = "Update stats for a player")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Stats updated successfully"),
			@ApiResponse(responseCode = "404", description = "Stats not found") })
	@PutMapping("/{id}")
	public ResponseEntity<PlayerStatsDto> update(@PathVariable Long id, @RequestBody PlayerStatsDto dto) {
		return ResponseEntity.ok(playerStatsService.updatePlayerStats(id, dto));
	}

	@Operation(summary = "Delete stats by ID")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Stats deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Stats not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		playerStatsService.deletePlayerStats(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/top-scorers/{seasonId}")
	@Operation(summary = "Get top scorers by season", description = "Returns a list of players ordered by goals for a specific season.")
	@ApiResponse(responseCode = "200", description = "Top scorers retrieved successfully")
	public ResponseEntity<List<PlayerStatsDto>> getTopScorersBySeason(@PathVariable Long seasonId,
			@RequestParam(defaultValue = "10") int limit) {
		return ResponseEntity.ok(playerStatsService.getTopScorersBySeason(seasonId, limit));
	}

	@GetMapping("/top-assists/{seasonId}")
	@Operation(summary = "Get top assisters by season", description = "Returns a list of players ordered by assists for a specific season.")
	@ApiResponse(responseCode = "200", description = "Top assisters retrieved successfully")
	public ResponseEntity<List<PlayerStatsDto>> getTopAssistsBySeason(@PathVariable Long seasonId,
			@RequestParam(defaultValue = "10") int limit) {
		return ResponseEntity.ok(playerStatsService.getTopAssistsBySeason(seasonId, limit));
	}

}