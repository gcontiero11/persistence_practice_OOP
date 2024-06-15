package dev.gustavo.model.team.services;

import dev.gustavo.model.player.entity.Player;
import dev.gustavo.model.team.entity.Team;
import dev.gustavo.persistence.dao.TeamDao;
import dev.gustavo.persistence.daoImpl.TeamDaoImpl;

public class SetCaptainService {
    private final TeamDao teamDao;

    public SetCaptainService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public void set(Player player, Team team){
        team.setCaptain(player);
        teamDao.update(Team.toDto(team));
    }
}
