package dev.gustavo.persistence.daoImpl;

import dev.gustavo.model.dtos.PlayerDto;
import dev.gustavo.persistence.dao.PlayerDao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayerDaoImpl implements PlayerDao {

    @Override
    public List<PlayerDto> findAll() {
        return List.of();
    }

    @Override
    public Optional<PlayerDto> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void save(PlayerDto player) {

    }

    @Override
    public void delete(PlayerDto player) {

    }
}
