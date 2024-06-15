package dev.gustavo.persistence.dao;

import dev.gustavo.model.dtos.PlayerDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerDao {
    List<PlayerDto> findAll();

    Optional<PlayerDto> findById(UUID id);



    void save(PlayerDto player);

    void delete(PlayerDto player);
}
