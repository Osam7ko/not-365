package com.pl.premer_zone.v3.teamS;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v3/team-seasons")
@RequiredArgsConstructor
public class TeamSeasonController {

	private final TeamSeasonService teamSeasonService;

	@PostMapping
	public ResponseEntity<TeamSeasonDto> create(@RequestBody TeamSeasonDto dto) {
		return ResponseEntity.ok(teamSeasonService.createTeamSeason(dto));
	}

	@GetMapping
	public ResponseEntity<List<TeamSeasonDto>> getAll() {
		return ResponseEntity.ok(teamSeasonService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeamSeasonDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(teamSeasonService.getById(id));
	}

	@GetMapping("/season/{seasonId}")
	public ResponseEntity<List<TeamSeasonDto>> getBySeason(@PathVariable Long seasonId) {
		return ResponseEntity.ok(teamSeasonService.getBySeason(seasonId));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TeamSeasonDto> update(@PathVariable Long id, @RequestBody TeamSeasonDto dto) {
		return ResponseEntity.ok(teamSeasonService.updateTeamSeason(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		teamSeasonService.deleteTeamSeason(id);
		return ResponseEntity.noContent().build();
	}

}
