package com.pl.premer_zone.v3.league;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v3/leagues")
@RequiredArgsConstructor
public class LeagueController {

	private final LeagueService leagueService;

	// GET api/Leagues
	@Operation(summary = "Get all leagues")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all leagues retrieved successfully"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping
	public ResponseEntity<List<LeagueDto>> geyAllLeagues() {
		return ResponseEntity.ok(leagueService.getAllLeagues());
	}

	// GET api/leagues/{id}
	@Operation(summary = "Get league by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "League retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "League not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/{id}")
	public ResponseEntity<LeagueDto> getLeagueById(@PathVariable Long id) {
		return ResponseEntity.ok(leagueService.getLeagueById(id));
	}

	// POST api/leagues/
	@Operation(summary = "Create a new league")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "League created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping
	public ResponseEntity<LeagueDto> createLeague(@RequestBody LeagueDto dto) {
		return ResponseEntity.ok(leagueService.createLeague(dto));
	}

	// PUT api/leagues/{id}
	@Operation(summary = "Update an existing league")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "League updated successfully"),
			@ApiResponse(responseCode = "404", description = "League not found"),
			@ApiResponse(responseCode = "400", description = "Invalid request data"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	public ResponseEntity<LeagueDto> updateLeague(@PathVariable Long id, @RequestBody LeagueDto dto) {
		return ResponseEntity.ok(leagueService.updateLeague(id, dto));
	}

	// DELETE api/leagues/{id}
	@Operation(summary = "Delete a league by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "League deleted successfully"),
			@ApiResponse(responseCode = "404", description = "League not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	public ResponseEntity<Void> deleteLeague(@PathVariable Long id) {
		leagueService.deleteLeague(id);
		return ResponseEntity.noContent().build();
	}
}
