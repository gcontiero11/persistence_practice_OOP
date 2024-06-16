package dev.gustavo.model.team.entity;

import dev.gustavo.model.player.dto.PlayerDto;
import dev.gustavo.model.player.entity.Player;
import dev.gustavo.model.player.services.GetPlayersService;
import dev.gustavo.model.player.services.UpdatePlayerService;
import dev.gustavo.model.team.dto.TeamDto;
import dev.gustavo.persistence.dao.PlayerDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Team {
    private final int id;
    private String name;
    private String baseLocation;
    private Player captain;
    private String coachName;
//    private final Map<UUID,Player> players = new HashMap<>();

    public Team(int id, String name, String baseLocation, Player captain, String coachName) {
        this.id = id;
        this.name = name;
        this.baseLocation = baseLocation;
        this.captain = captain;
        this.coachName = coachName;
    }

    public void addPlayer(Player player, PlayerDao playerDao) {
        player.setTeam(this);

        UpdatePlayerService updatePlayerService = new UpdatePlayerService(playerDao);
        updatePlayerService.update(Player.toDto(player));
    }

    public void removePlayer(Player player,PlayerDao playerDao) {
        player.setTeam(null);

        UpdatePlayerService updatePlayerService = new UpdatePlayerService(playerDao);
        updatePlayerService.update(Player.toDto(player));
    }

    public void substitute(Player substitute, Player beginner, PlayerDao playerDao) {
        substitute.setFielded(true);
        beginner.setFielded(false);

        UpdatePlayerService updatePlayerService = new UpdatePlayerService(playerDao);
        updatePlayerService.update(Player.toDto(substitute));
        updatePlayerService.update(Player.toDto(beginner));
    }

    public Set<Player> getFieldedPlayers(PlayerDao playerDao) {
         return getPlayers(playerDao).stream().filter(Player::isFielded).collect(Collectors.toSet());
    }

    public Set<Player> getOutFieldedPlayers(PlayerDao playerDao) {
         return getPlayers(playerDao).stream().filter(p -> !p.isFielded()).collect(Collectors.toSet());
    }

    private Set<Player> getPlayers(PlayerDao playerDao) {
        GetPlayersService getService = new GetPlayersService(playerDao);
        List<PlayerDto> players = getService.getAll();

        return new HashSet<>(players.stream().map(Player::fromDto).toList());
    }

    public static TeamDto toDto(Team t) {
        return new TeamDto(t.id, t.name, t.baseLocation, t.captain, t.coachName);
    }

    public static Team fromDto(TeamDto dto) {
        return new Team(dto.id(), dto.name(), dto.baseLocation(), dto.captain(), dto.coachName());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;

    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
}
