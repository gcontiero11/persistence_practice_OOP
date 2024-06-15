package dev.gustavo.persistence.dao;

import dev.gustavo.model.team.dto.TeamDto;

import java.util.List;
import java.util.Optional;

public interface TeamDao {
    List<TeamDto> findAll();

    Optional<TeamDto> findById(int id);

    void save(TeamDto dto);

    void update(TeamDto dto);

    void delete(TeamDto dto);

}
