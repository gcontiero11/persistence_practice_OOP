package dev.gustavo.persistence.dao;

import dev.gustavo.model.team.dto.TeamDto;

import java.util.Optional;
import java.util.Set;

public interface TeamDao {
    Set<TeamDto> findAll();

    Optional<TeamDto> findById(int id);

    void save(TeamDto dto);

    void update(TeamDto dto);

    void delete(TeamDto dto);


}
