package dev.gustavo.model.team.entity;

import dev.gustavo.model.player.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Team {
    private final int id;
    private String name;
    private String baseLocation;
    private Player captain;
    private String coachName;
    private final Map<UUID, Player> players;

    public Team(int id, String name, String baseLocation, Player captain, String coachName) {
        this(id, name, baseLocation, captain, coachName, new HashMap<>());
    }

    public Team(int id, String name, String baseLocation, Player captain, String coachName, Map<UUID, Player> players) {
        this.id = id;
        this.name = name;
        this.baseLocation = baseLocation;
        this.captain = captain;
        this.coachName = coachName;
        this.players = players;
    }

    public void addPlayer(Player player) {
        player.setTeam(this);
        players.put(player.getUuid(), player);
    }

    public void removePlayer(Player player) {
        player.setTeam(null);
        players.remove(player.getUuid());
    }

    public void substitute(Player substitute, Player beginner) {
        if (substitute.isFielded() || !beginner.isFielded())
            throw new IllegalStateException("player out of their correct position");

        substitute.setFielded(true);
        beginner.setFielded(false);

        players.replace(substitute.getUuid(), substitute);
        players.replace(beginner.getUuid(), beginner);
    }

    public Set<Player> getFieldedPlayers() {
        return players.values().stream()
                .filter(Player::isFielded)
                .collect(Collectors.toSet());
    }

    public Set<Player> getOutFieldedPlayers() {
        return players.values().stream()
                .filter(p -> !p.isFielded())
                .collect(Collectors.toSet());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;
        return id == team.id && Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(name);
        return result;
    }

    @Override
    public String toString() {
        if (captain != null){
            return "Team{" +
                   "id=" + id +
                   ", name='" + name + '\'' +
                   ", baseLocation='" + baseLocation + '\'' +
                   ", captain=" + captain.getName() +
                   ", coachName='" + coachName + '\'' +
                   ", players=" + players.values().stream().map(Player::getName).toList() +
                   '}';
        }
        return "Team{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", baseLocation='" + baseLocation + '\'' +
               ", captain= " +
               ", coachName='" + coachName + '\'' +
               ", players=" + players.values().stream().map(Player::getName).toList() +
               '}';

    }
}
