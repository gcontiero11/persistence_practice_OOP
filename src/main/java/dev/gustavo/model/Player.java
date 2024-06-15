package dev.gustavo.model;

import dev.gustavo.model.dtos.PlayerDto;

import java.util.Objects;
import java.util.UUID;

public class Player {
    private final UUID uuid;
    private Team team;
    private String name;
    private int number;
    private String position;
    private boolean isFielded;

    public Player(Team team,String name, int number, String position) {
        this(UUID.randomUUID(),team,name,number,position,false);
    }

    public Player(UUID uuid,Team team, String name, int number, String position, boolean isFielded) {
        this.uuid = uuid;
        this.team = team;
        this.name = name;
        this.number = number;
        this.position = position;
        this.isFielded = isFielded;
    }

    public static PlayerDto toDto(Player p) {
        return new PlayerDto(p.getUuid(),p.getTeam(),p.getName(),p.getNumber(),p.getPosition(),p.isFielded());
    }

    public static Player fromDto(PlayerDto dto){
        return new Player(dto.uuid(),dto.team(), dto.name(), dto.number(), dto.position(), dto.isFielded());
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
        return Objects.equals(uuid, player.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
