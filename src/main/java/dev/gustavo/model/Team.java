package dev.gustavo.model;

import dev.gustavo.model.dtos.PlayerDto;
import dev.gustavo.model.dtos.TeamDto;
import dev.gustavo.persistence.dao.TeamDao;
import dev.gustavo.persistence.daoImpl.TeamDaoImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Team {
    private final int id;
    private String name;
    private String baseLocation;
    private Player captain;
    private String coachName;
    private final TeamDao teamDao = new TeamDaoImpl();
    private final Map<UUID, Player> playersCache;

    public Team(int id, String name, String baseLocation, Player captain, String coachName) {
        this.id = id;
        this.name = name;
        this.baseLocation = baseLocation;
        this.captain = captain;
        this.coachName = coachName;
        this.playersCache = new HashMap<>();
    }

    public void addPlayer(Player player) {
        teamDao.addPlayer(Player.toDto(player));
        playersCache.put(player.getUuid(), player);
    }

    public void removePlayer(Player player) {
        teamDao.removePlayer(Player.toDto(player));
    }

    public void substitute(Player substitute, Player beginner){
        teamDao.substitute(Player.toDto(substitute),Player.toDto(beginner));
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
