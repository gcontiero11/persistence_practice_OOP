package dev.gustavo.model.player.entity;

import dev.gustavo.model.player.dto.PlayerDto;
import dev.gustavo.model.team.entity.Team;

import java.util.UUID;

public class Player {
    private final UUID uuid;
    private Team team;
    private String name;
    private int number;
    private String position;
    private boolean isFielded;

    public Player(String name, int number, String position) {
        this(UUID.randomUUID(), null, name, number, position, false);
    }

    public Player(UUID uuid, Team team, String name, int number, String position, boolean isFielded) {
        this.uuid = uuid;
        this.team = team;
        this.name = name;
        this.number = number;
        this.position = position;
        this.isFielded = isFielded;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isFielded() {
        return isFielded;
    }

    public void setFielded(boolean fielded) {
        isFielded = fielded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;
        return uuid.equals(player.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return "Player{" +
               "uuid=" + uuid +
               ", team=" + team +
               ", name='" + name + '\'' +
               ", number=" + number +
               ", position='" + position + '\'' +
               ", isFielded=" + isFielded +
               '}';
    }
}
