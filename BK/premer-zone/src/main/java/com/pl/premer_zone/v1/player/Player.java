//package com.pl.premer_zone.v1.player;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "player_statistic")
//public class Player {
//
//	@Id
//	@Column(name = "player_name", unique = true)
//	private String name;
//	@Column(name = "nation")
//	private String nation;
//
//	@Column(name = "position")
//	private String pos;
//
//	@Column(name = "age")
//	private Integer age;
//
//	@Column(name = "matches_played")
//	private Integer mp;
//
//	@Column(name = "starts")
//	private Integer starts;
//
//	@Column(name = "minutes_played")
//	private Double min;
//
//	@Column(name = "goals")
//	private Double gls;
//
//	@Column(name = "assists")
//	private Double ast;
//
//	@Column(name = "penalties_scored")
//	private Double pk;
//
//	@Column(name = "yellow_cards")
//	private Double crdy;
//
//	@Column(name = "red_cards")
//	private Double crdr;
//
//	@Column(name = "expected_goals")
//	private Double xg;
//
//	@Column(name = "expected_assists")
//	private Double xag;
//
//	@Column(name = "team_name")
//	private String team;
//
//}
