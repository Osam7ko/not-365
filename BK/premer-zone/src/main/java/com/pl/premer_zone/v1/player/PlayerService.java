//package com.pl.premer_zone.v1.player;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.stereotype.Component;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Component
//public class PlayerService {
//	private final PlayerRepost playerRepost;
//
//	public List<Player> getPlayer() {
//		return playerRepost.findAll();
//	}
//
//	public List<Player> getPlayerFromTeam(String teamName) {
//		return playerRepost.findAll().stream().filter(player -> teamName.equals(player.getTeam()))
//				.collect(Collectors.toList());
//	}
//
//	public List<Player> getPlayerByName(String searchText) {
//		return playerRepost.findAll().stream()
//				.filter(player -> player.getName().toLowerCase().contains(searchText.toLowerCase()))
//				.collect(Collectors.toList());
//	}
//
//	public List<Player> getPlayerByPos(String searchText) {
//		return playerRepost.findAll().stream().filter(
//				player -> player.getPos() != null && player.getPos().toLowerCase().contains(searchText.toLowerCase()))
//				.collect(Collectors.toList());
//	}
//
//	public List<Player> getPlayerByNation(String searchText) {
//		return playerRepost.findAll().stream()
//				.filter(player -> player.getNation() != null
//						&& player.getNation().toLowerCase().contains(searchText.toLowerCase()))
//				.collect(Collectors.toList());
//	}
//
//	public List<Player> getPlayerByTeamAndPostion(String team, String postion) {
//		return playerRepost.findAll().stream()
//				.filter(player -> team.equals(player.getTeam()) && postion.equals(player.getPos()))
//				.collect(Collectors.toList());
//
//	}
//
//	public Player addPlayer(Player player) {
//		playerRepost.save(player);
//		return player;
//	}
//
//	public Player updatePlayer(Player updatePlayer) {
//		Optional<Player> exitingPlayer = playerRepost.findByName(updatePlayer.getName());
//
//		if (exitingPlayer.isPresent()) {
//			Player playerToUpdate = exitingPlayer.get();
//			playerToUpdate.setName(updatePlayer.getName());
//			playerToUpdate.setTeam(updatePlayer.getTeam());
//			playerToUpdate.setPos(updatePlayer.getPos());
//			playerToUpdate.setNation(updatePlayer.getNation());
//
//			playerRepost.save(playerToUpdate);
//			return playerToUpdate;
//		}
//		return null;
//	}
//
//	@Transactional
//	public void deletePlayer(String playerName) {
//		playerRepost.deleteByName(playerName);
//	}
//}
