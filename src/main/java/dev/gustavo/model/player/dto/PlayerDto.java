package dev.gustavo.model.player.dto;

import dev.gustavo.model.team.entity.Team;

import java.util.Objects;
import java.util.UUID;

public record PlayerDto(UUID uuid, int teamId, String name, int number, String position, boolean isFielded) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDto playerDto = (PlayerDto) o;
        return Objects.equals(uuid, playerDto.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
