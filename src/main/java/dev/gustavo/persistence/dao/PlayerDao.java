package dev.gustavo.persistence.dao;

import dev.gustavo.model.player.dto.PlayerDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerDao {
    List<PlayerDto> findAll();

    Optional<PlayerDto> findById(UUID id);

    void save(PlayerDto dto);

    void update(PlayerDto dto);

    void delete(PlayerDto dto);
}
