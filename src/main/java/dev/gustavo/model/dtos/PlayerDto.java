package dev.gustavo.model.dtos;

import dev.gustavo.model.Team;

import java.util.UUID;

public record PlayerDto(UUID uuid, Team team, String name, int number, String position, boolean isFielded) {
}
