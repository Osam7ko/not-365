package com.pl.premer_zone.v3.teamBP;

import java.util.List;

import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v3/team-best-players")
@RequiredArgsConstructor
public class TeamBestPlayerController {

	private final TeamBestPlayerService teamBestPlayerService;

	@Operation(summary = "Create a team best player entry")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Team best player created"),
			@ApiResponse(responseCode = "400", description = "Invalid input") })
	@PostMapping
	public ResponseEntity<TeamBestPlayerDto> create(@RequestBody TeamBestPlayerDto dto) {
		TeamBestPlayerDto created = teamBestPlayerService.createTeamBestPlayer(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@Operation(summary = "Get all team best player entries")
	@ApiResponse(responseCode = "200", description = "All team best players retrieved")
	@GetMapping
	public ResponseEntity<List<TeamBestPlayerDto>> getAll() {
		return ResponseEntity.ok(teamBestPlayerService.getAll());
	}

	@Operation(summary = "Get team best players by season")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Team best players retrieved for the season"),
			@ApiResponse(responseCode = "404", description = "Season not found") })
	@GetMapping("/season/{seasonId}")
	public ResponseEntity<List<TeamBestPlayerDto>> getBySeason(@PathVariable Long seasonId) {
		return ResponseEntity.ok(teamBestPlayerService.getBySeason(seasonId));
	}

	@Operation(summary = "Get team best players by team ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Team best players retrieved for the team"),
			@ApiResponse(responseCode = "404", description = "Team not found") })
	@GetMapping("/team/{teamId}")
	public ResponseEntity<List<TeamBestPlayerDto>> getByTeam(@PathVariable Long teamId) {
		return ResponseEntity.ok(teamBestPlayerService.getByTeam(teamId));
	}

	@Operation(summary = "Delete team best player entry by ID")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Team best player deleted"),
			@ApiResponse(responseCode = "404", description = "Entry not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		teamBestPlayerService.deleteTeamBestPlayer(id);
		return ResponseEntity.noContent().build();
	}
}