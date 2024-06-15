package dev.gustavo.model.team.dto;

import dev.gustavo.model.player.entity.Player;

public record TeamDto(int id, String name, String baseLocation, Player captain, String coachName) {
}
