package com.pl.premer_zone.v3.team;

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
@RequestMapping("/api/v3/teams")
@RequiredArgsConstructor
public class TeamController {

	private final TeamService teamService;

	@Operation(summary = "Create a new team")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Team created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request") })
	@PostMapping
	public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto dto) {
		TeamDto created = teamService.createTeam(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@Operation(summary = "Get all teams")
	@ApiResponse(responseCode = "200", description = "All teams retrieved successfully")
	@GetMapping
	public List<TeamDto> getAllTeams() {
		return teamService.getAllTeams();
	}

	@Operation(summary = "Get a team by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Team retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Team not found") })
	@GetMapping("/{id}")
	public ResponseEntity<TeamDto> getTeamById(@PathVariable Long id) {
		TeamDto dto = teamService.getTeamById(id);
		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Update a team by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Team updated successfully"),
			@ApiResponse(responseCode = "404", description = "Team not found"),
			@ApiResponse(responseCode = "400", description = "Invalid request") })
	@PutMapping("/{id}")
	public ResponseEntity<TeamDto> updateTeam(@PathVariable Long id, @RequestBody TeamDto dto) {
		TeamDto updated = teamService.updateTeam(id, dto);
		return ResponseEntity.ok(updated);
	}

	@Operation(summary = "Delete a team by ID")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Team deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Team not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
		teamService.deleteTeam(id);
		return ResponseEntity.noContent().build();
	}
}
