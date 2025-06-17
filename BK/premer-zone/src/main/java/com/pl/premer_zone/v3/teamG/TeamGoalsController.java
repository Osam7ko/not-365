package com.pl.premer_zone.v3.teamG;

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
@RequestMapping("/api/v3/team-goals")
@RequiredArgsConstructor
public class TeamGoalsController {

	private final TeamGoalsService teamGoalsService;

	@PostMapping
	public ResponseEntity<TeamGoalsDto> create(@RequestBody TeamGoalsDto dto) {
		return ResponseEntity.ok(teamGoalsService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TeamGoalsDto> update(@PathVariable Long id, @RequestBody TeamGoalsDto dto) {
		return ResponseEntity.ok(teamGoalsService.update(id, dto));
	}

	@GetMapping
	public ResponseEntity<List<TeamGoalsDto>> getAll() {
		return ResponseEntity.ok(teamGoalsService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeamGoalsDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(teamGoalsService.getById(id));
	}

	@GetMapping("/season/{seasonId}")
	public ResponseEntity<List<TeamGoalsDto>> getBySeason(@PathVariable Long seasonId) {
		return ResponseEntity.ok(teamGoalsService.getBySeason(seasonId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		teamGoalsService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
