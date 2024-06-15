package dev.gustavo;

import dev.gustavo.model.player.entity.Player;
import dev.gustavo.model.team.entity.Team;
import dev.gustavo.persistence.DataBaseBuilder;

public class Main {
    public static void main(String[] args) {
        DataBaseBuilder dbBuilder = new DataBaseBuilder();
        dbBuilder.createDatabaseIfIsMissing();

        Player yuriAlberto = new Player("Yuri Alberto",9,"Centro-Avante");
        Team corinthians = new Team(1,"Corinthians","São Paulo - SP",yuriAlberto,"Antônio Oliveira");
    }
}