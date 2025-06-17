//package com.pl.premer_zone.v1.player;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping(path = "api/v1/player")
//@RequiredArgsConstructor
//public class PlayerController {
//
//	private final PlayerService playerService;
//
//	@GetMapping
//	public List<Player> getPlayers(@RequestParam(required = false) String team,
//			@RequestParam(required = false) String name, @RequestParam(required = false) String position,
//			@RequestParam(required = false) String nation) {
//		if (team != null && position != null) {
//			return playerService.getPlayerByTeamAndPostion(team, position);
//		} else if (team != null) {
//			return playerService.getPlayerFromTeam(team);
//		} else if (name != null) {
//			return playerService.getPlayerByName(name);
//		} else if (position != null) {
//			return playerService.getPlayerByPos(position);
//		} else if (nation != null) {
//			return playerService.getPlayerByNation(nation);
//		} else {
//			return playerService.getPlayer();
//		}
//	}
//
//	@PostMapping
//	public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
//		Player createPlayer = playerService.addPlayer(player);
//		return new ResponseEntity<>(createPlayer, HttpStatus.CREATED);
//	}
//
//	@PutMapping
//	public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
//		Player resultPlayer = playerService.updatePlayer(player);
//
//		if (resultPlayer != null) {
//			return new ResponseEntity<>(resultPlayer, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@DeleteMapping("/{playerName}")
//	public ResponseEntity<String> deletePlayer(@PathVariable String playerName) {
//		playerService.deletePlayer(playerName);
//		return new ResponseEntity<>("Player Deleted", HttpStatus.OK);
//	}
//}
