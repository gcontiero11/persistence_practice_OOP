package dev.gustavo.model.team.dto;

import java.util.UUID;

public record TeamDto(int id, String name, String baseLocation, UUID captainId, String coachName) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamDto teamDto = (TeamDto) o;
        return id == teamDto.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
