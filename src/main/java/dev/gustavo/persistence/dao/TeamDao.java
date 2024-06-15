package dev.gustavo.persistence.dao;

import dev.gustavo.model.dtos.PlayerDto;
import dev.gustavo.model.dtos.TeamDto;

import java.util.List;
import java.util.Optional;

public interface TeamDao {
    List<TeamDto> findAll();

    Optional<TeamDto> findById(int id);

    void addPlayer(PlayerDto dto);

    void removePlayer(PlayerDto dto);

    void substitute(PlayerDto substitute, PlayerDto beginner);

    void save(TeamDto dto);

    void delete(TeamDto dto);

}
