package com.pl.premer_zone.v2.apifotbull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.premer_zone.v2.dto.PlayerDto;
import com.pl.premer_zone.v2.dto.TeamSummaryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TeamStatsService {
    @Value("${api.football.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<TeamSummaryDto> getTeamSummaries(int leagueId, int season) {
        List<TeamSummaryDto> summaries = new ArrayList<>();

        // Get teams first
        String teamUrl = String.format("https://v3.football.api-sports.io/teams?league=%d&season=%d", leagueId, season);
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<String> teamResponse = restTemplate.exchange(teamUrl, HttpMethod.GET, request, String.class);

        try {
            JsonNode root = objectMapper.readTree(teamResponse.getBody());
            JsonNode teamsArray = root.get("response");

            for (JsonNode teamNode : teamsArray) {
                int teamId = teamNode.get("team").get("id").asInt();
                String teamName = teamNode.get("team").get("name").asText();
                String logo = teamNode.get("team").get("logo").asText();

                // Fetch players
                String playersUrl = String.format("https://v3.football.api-sports.io/players?team=%d&season=%d", teamId, season);
                ResponseEntity<String> playersResponse = restTemplate.exchange(playersUrl, HttpMethod.GET, request, String.class);
                JsonNode playersRoot = objectMapper.readTree(playersResponse.getBody());
                JsonNode playerArray = playersRoot.get("response");

                List<PlayerDto> players = new ArrayList<>();

                for (JsonNode wrapper : playerArray) {
                    JsonNode player = wrapper.get("player");
                    JsonNode stats = wrapper.get("statistics").get(0);

                    String name = player.get("name").asText();
                    int age = player.get("age").asInt();
                    String nationality = player.get("nationality").asText();
                    String position = stats.get("games").get("position").asText();
                    int goals = stats.get("goals").get("total").asInt(0);

                    players.add(new PlayerDto(name, position, age, goals, 0, 0, teamName));
                }

                PlayerDto topScorer = players.stream()
                        .max(Comparator.comparingInt(PlayerDto::getGoals))
                        .orElse(null);

                int totalGoals = players.stream().mapToInt(PlayerDto::getGoals).sum();

                summaries.add(new TeamSummaryDto(teamName, logo,
                        topScorer != null ? topScorer.getName() : "N/A", totalGoals));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return summaries;
    }
}

