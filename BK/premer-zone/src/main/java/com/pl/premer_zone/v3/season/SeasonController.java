package com.pl.premer_zone.v3.season;

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
@RequestMapping("/api/v3/seasons")
@RequiredArgsConstructor
public class SeasonController {

	private final SeasonService seasonService;

	@Operation(summary = "Create a new season")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Season created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request") })
	@PostMapping
	public ResponseEntity<SeasonDto> createSeason(@RequestBody SeasonDto dto) {
		SeasonDto created = seasonService.createSeason(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@Operation(summary = "Get all seasons")
	@ApiResponse(responseCode = "200", description = "All seasons retrieved successfully")
	@GetMapping
	public List<SeasonDto> getAllSeasons() {
		return seasonService.getAllSeasons();
	}

	@Operation(summary = "Get season by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Season retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Season not found") })
	@GetMapping("/{id}")
	public ResponseEntity<SeasonDto> getSeasonById(@PathVariable Long id) {
		SeasonDto season = seasonService.getSeasonById(id);
		return ResponseEntity.ok(season);
	}

	@Operation(summary = "Update season by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Season updated successfully"),
			@ApiResponse(responseCode = "404", description = "Season not found") })
	@PutMapping("/{id}")
	public ResponseEntity<SeasonDto> updateSeason(@PathVariable Long id, @RequestBody SeasonDto dto) {
		SeasonDto updated = seasonService.updateSeason(id, dto);
		return ResponseEntity.ok(updated);
	}

	@Operation(summary = "Delete season by ID")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Season deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Season not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSeason(@PathVariable Long id) {
		seasonService.deleteSeason(id);
		return ResponseEntity.noContent().build();
	}
}
