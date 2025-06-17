package com.pl.premer_zone.v3.leagueBP;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v3/league-best-players")
@RequiredArgsConstructor
public class LeagueBestPlayerController {

	private final LeagueBestPlayerService leagueBestPlayerService;

	@Operation(summary = "Create a new best player for a league season")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Best player saved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping
	public ResponseEntity<LeagueBestPlayerDto> create(@RequestBody LeagueBestPlayerDto dto) {
		return ResponseEntity.ok(leagueBestPlayerService.createLeagueBestPlayer(dto));
	}

	@Operation(summary = "Get best players by season")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Best players retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "No best players found for the season"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/season/{seasonId}")
	public ResponseEntity<List<LeagueBestPlayerDto>> getBySeason(@PathVariable Long seasonId) {
		return ResponseEntity.ok(leagueBestPlayerService.getBestPlayersBySeason(seasonId));
	}

	@Operation(summary = "Delete all best players for a given season")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Best players deleted successfully"),
			@ApiResponse(responseCode = "404", description = "No best players found for the season"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@DeleteMapping("/season/{seasonId}")
	public ResponseEntity<Void> deleteBySeason(@PathVariable Long seasonId) {
		leagueBestPlayerService.deleteBySeasonId(seasonId);
		return ResponseEntity.noContent().build();
	}
}
