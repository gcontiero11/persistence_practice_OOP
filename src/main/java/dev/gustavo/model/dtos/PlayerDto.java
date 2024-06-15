package dev.gustavo.model.dtos;

import java.util.UUID;

public record PlayerDto(UUID uuid, String name, int number,String position, boolean isFielded) {
}
