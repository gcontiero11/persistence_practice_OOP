package dev.gustavo.persistence.daoImpl;

import dev.gustavo.model.team.dto.TeamDto;
import dev.gustavo.persistence.dao.TeamDao;

import java.util.List;
import java.util.Optional;

public class TeamDaoImpl implements TeamDao {


    @Override
    public List<TeamDto> findAll() {
        return List.of();
    }

    @Override
    public Optional<TeamDto> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(TeamDto dto) {

    }

    @Override
    public void update(TeamDto dto) {

    }

    @Override
    public void delete(TeamDto dto) {

    }
}
