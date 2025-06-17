package com.pl.premer_zone.v2.apifotbull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.premer_zone.v2.dto.PlayerDto;
import com.pl.premer_zone.v2.dto.TeamDto;
import com.pl.premer_zone.v2.dto.TeamResponseDto;
import com.pl.premer_zone.v2.dto.TeamWrapperDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FootballApiService {

    @Value("${api.football.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public FootballApiService(){
        this.restTemplate = new RestTemplate();
    }

    public String getTeamsByLeagueAndSeason(int leagueId, int season){
        String url = String.format("https://v3.football.api-sports.io/teams?league=%d&season=%d",leagueId,season);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        return response.getBody();
    }

    public List<TeamDto> getTeamsDto (int leagueId, int season){
        String url = String.format("https://v3.football.api-sports.io/teams?league=%d&season=%d", leagueId, season);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<TeamResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                TeamResponseDto.class
        );

        return response.getBody().getResponse().stream()
                .map(TeamWrapperDto::getTeam)
                .collect(Collectors.toList());
    }

    public List<PlayerDto> getPlayersByTeamAndSeason(int teamId, int season) {
        String url = String.format("https://v3.football.api-sports.io/players?team=%d&season=%d", teamId, season);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        return parsePlayerResponse(response.getBody());
    }
    private List<PlayerDto> parsePlayerResponse(String json) {
        List<PlayerDto> players = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode responseArray = root.path("response");

            for (JsonNode item : responseArray) {
                JsonNode player = item.path("player");
                JsonNode stats = item.path("statistics").get(0);

                PlayerDto dto = new PlayerDto();
                dto.setName(player.path("name").asText());
                dto.setAge(player.path("age").asInt());
                dto.setPosition(player.path("position").asText());
                dto.setTeam(stats.path("team").path("name").asText());
                dto.setGoals(stats.path("goals").path("total").asInt());
                dto.setAssists(stats.path("goals").path("assists").asInt(0));
                dto.setMinutes(stats.path("games").path("minutes").asInt(0));

                players.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

}
