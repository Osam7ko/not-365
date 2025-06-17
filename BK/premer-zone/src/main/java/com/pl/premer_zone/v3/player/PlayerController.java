package com.pl.premer_zone.v3.player;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v3/players")
@RequiredArgsConstructor
public class PlayerController {

	private final PlayerService playerService;

	@Operation(summary = "Create a new player")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Player created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input") })
	@PostMapping
	public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto dto) {
		PlayerDto created = playerService.createPlayer(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@Operation(summary = "Get all players")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "List of players retrieved") })
	@GetMapping
	public ResponseEntity<List<PlayerDto>> getAllPlayers() {
		return ResponseEntity.ok(playerService.getAllPlayers());
	}

	@Operation(summary = "Get player by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Player found"),
			@ApiResponse(responseCode = "404", description = "Player not found") })
	@GetMapping("/{id}")
	public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Long id) {
		PlayerDto dto = playerService.getPlayerById(id);
		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Get latest stats for a player")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Player stats retrieved"),
			@ApiResponse(responseCode = "404", description = "Player or stats not found") })
	@GetMapping("/{id}/latest-stats")
	public ResponseEntity<PlayerDto> getPlayerWithLatestStats(@PathVariable Long id) {
		PlayerDto dto = playerService.getPlayerWithLatestStats(id);
		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Update player information")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Player updated"),
			@ApiResponse(responseCode = "404", description = "Player not found") })
	@PutMapping("/{id}")
	public ResponseEntity<PlayerDto> updatePlayer(@PathVariable Long id, @RequestBody PlayerDto dto) {
		PlayerDto updated = playerService.updatePlayer(id, dto);
		return ResponseEntity.ok(updated);
	}

	@Operation(summary = "Delete player")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Player deleted"),
			@ApiResponse(responseCode = "404", description = "Player not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
		playerService.deletePlayer(id);
		return ResponseEntity.noContent().build();
	}
}