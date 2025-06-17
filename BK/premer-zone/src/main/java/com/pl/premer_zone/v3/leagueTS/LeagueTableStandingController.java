package com.pl.premer_zone.v3.leagueTS;

import java.util.List;

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
@RequestMapping("/api/v3/league-table")
@RequiredArgsConstructor
public class LeagueTableStandingController {

	private final LeagueTableStandingService service;

	@Operation(summary = "Create a league table standing entry")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Standing created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping
	public ResponseEntity<LeagueTableStandingDto> create(@RequestBody LeagueTableStandingDto dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@Operation(summary = "Update a league table standing by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Standing updated successfully"),
			@ApiResponse(responseCode = "404", description = "Standing not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PutMapping("/{id}")
	public ResponseEntity<LeagueTableStandingDto> update(@PathVariable Long id,
			@RequestBody LeagueTableStandingDto dto) {
		return ResponseEntity.ok(service.update(id, dto));
	}

	@Operation(summary = "Get league table standing by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Standing retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Standing not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/{id}")
	public ResponseEntity<LeagueTableStandingDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@Operation(summary = "Get all league table standings")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Standings retrieved successfully"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping
	public ResponseEntity<List<LeagueTableStandingDto>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@Operation(summary = "Get league table standings by season ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Standings for the season retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Season not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/season/{seasonId}")
	public ResponseEntity<List<LeagueTableStandingDto>> getBySeason(@PathVariable Long seasonId) {
		return ResponseEntity.ok(service.getBySeason(seasonId));
	}

	@Operation(summary = "Delete league table standing by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Standing deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Standing not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
