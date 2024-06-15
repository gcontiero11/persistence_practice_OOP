package dev.gustavo.model.dtos;

import dev.gustavo.model.Player;

public record TeamDto(int id, String name, String baseLocation, Player captain, String coachName) {
}
