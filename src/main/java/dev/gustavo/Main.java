package dev.gustavo;

import dev.gustavo.controller.BrasileiraoController;
import dev.gustavo.model.player.entity.Player;
import dev.gustavo.model.team.entity.Team;
import dev.gustavo.persistence.DataBaseBuilder;
import dev.gustavo.persistence.dao.PlayerDao;
import dev.gustavo.persistence.dao.TeamDao;
import dev.gustavo.persistence.daoImpl.SQLitePlayerDaoImpl;
import dev.gustavo.persistence.daoImpl.SQLiteTeamDaoImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataBaseBuilder dbBuilder = new DataBaseBuilder();
//      Não iniciar com database conectada no intelliJ
        PlayerDao playerDao = new SQLitePlayerDaoImpl();
        TeamDao teamDao = new SQLiteTeamDaoImpl();
        BrasileiraoController controller = new BrasileiraoController(playerDao,teamDao);
        Scanner scan = new Scanner(System.in);
        dbBuilder.cleanDataBase();

        Player yuriAlberto = new Player("Yuri Alberto",
                9,
                "Centro-Avante");
        Player rodrigoGarro = new Player("Rodrigo Garro",
                10,
                "Meia-Atacante");
        controller.createPlayer(yuriAlberto);
        controller.createPlayer(rodrigoGarro);

        scan.nextLine();

        Team corinthians = new Team(1,
                "Corinthians",
                "São Paulo - SP",
                null,
                "Gustavo");
        controller.createTeam(corinthians);

        scan.nextLine();

        controller.addPlayerToTeam(yuriAlberto,corinthians);
        controller.setPlayerIntoField(yuriAlberto);

        scan.nextLine();

        controller.addPlayerToTeam(rodrigoGarro,corinthians);
        controller.getFieldPlayersOfTeam(corinthians);
        controller.substitution(rodrigoGarro,yuriAlberto,corinthians);

        scan.nextLine();

        controller.getOutFieldedPlayerOfTeam(corinthians);
        controller.deletePlayer(yuriAlberto);
        controller.getOutFieldedPlayerOfTeam(corinthians);
    }
}