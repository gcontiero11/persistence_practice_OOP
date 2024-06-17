package dev.gustavo.controller;

import dev.gustavo.model.player.entity.Player;
import dev.gustavo.model.team.entity.Team;

public interface SoccerLeagueController {
    void createTeam(Team t);
    void createPlayer(Player p);
    void addPlayerToTeam(Player p, Team t);
    void deletePlayer(Player p);
    void substitution(Player substitute, Player beginner,Team t);
    void getFieldPlayersOfTeam(Team t);
    void getOutFieldedPlayerOfTeam(Team t);
    void setPlayerIntoField(Player p);
    void setPlayerOutOfField(Player p);
}
