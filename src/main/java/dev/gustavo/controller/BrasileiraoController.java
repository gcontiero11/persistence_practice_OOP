package dev.gustavo.controller;

import dev.gustavo.model.player.entity.Player;
import dev.gustavo.model.team.entity.Team;
import dev.gustavo.persistence.dao.PlayerDao;
import dev.gustavo.persistence.dao.TeamDao;
import dev.gustavo.utils.Converter;

public class BrasileiraoController implements SoccerLeagueController {
    PlayerDao playerDao;
    TeamDao teamDao;

    public BrasileiraoController(PlayerDao playerDao, TeamDao teamDao) {
        this.playerDao = playerDao;
        this.teamDao = teamDao;
    }

    public void createTeam(Team t){
        teamDao.save(Converter.teamToDto(t));
    }

    public void createPlayer(Player p){
        playerDao.save(Converter.playerToDto(p));
    }

    public void addPlayerToTeam(Player p, Team t){
        t.addPlayer(p);
        playerDao.update(Converter.playerToDto(p));
        teamDao.update(Converter.teamToDto(t));
    }

    public void deletePlayer(Player p){
        Team t = p.getTeam();
        if (p.equals(t.getCaptain())) t.setCaptain(null);
        t.removePlayer(p);
        playerDao.delete(Converter.playerToDto(p));
    }

    public void substitution(Player substitute, Player beginner,Team t){
        t.substitute(substitute,beginner);
        playerDao.update(Converter.playerToDto(substitute));
        playerDao.update(Converter.playerToDto(beginner));
        teamDao.update(Converter.teamToDto(t));
    }

    public void getFieldPlayersOfTeam(Team t){
        System.out.println(t.getFieldedPlayers());
    }

    public void getOutFieldedPlayerOfTeam(Team t){
        System.out.println(t.getOutFieldedPlayers());
    }

    public void setPlayerIntoField(Player p){
        p.setFielded(true);
        playerDao.update(Converter.playerToDto(p));
    }

    public void setPlayerOutOfField(Player p){
        p.setFielded(false);
        playerDao.update(Converter.playerToDto(p));
    }

    @Override
    public void setTeamCaptain(Player p, Team t) {
        t.setCaptain(p);
        teamDao.update(Converter.teamToDto(t));
    }
}
