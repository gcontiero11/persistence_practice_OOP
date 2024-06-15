package dev.gustavo.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseBuilder {

    public void createDatabaseIfIsMissing(){
        if (isDataBaseMissing()){
            deletePreviousDataBase();
            createNewDataBase();
        }
    }

    private boolean isDataBaseMissing() {
        return !Files.exists(Paths.get("Database.db"));
    }

    private void createNewDataBase() {
        try(Statement statement = ConnectionFactory.createStatement()){
            statement.addBatch(playersTableSql());
            statement.addBatch(teamsTableSql());
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void deletePreviousDataBase() {
        try {
            Files.delete(Paths.get("Database.db"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private String teamsTableSql() {
        return """
                CREATE TABLE team IF NOT EXISTS(
                    id INT NOT NULL,
                    name TEXT NOT NULL,
                    coachName TEXT,
                    CONSTRAINT team_id_pk PRIMARY KEY(id)
                );
                """;
    }

    private String playersTableSql(){
        return """
                CREATE TABLE player IF NOT EXISTS(
                    uuid UUID NOT NULL,
                    team_id INTEGER,
                    name TEXT NOT NULL,
                    number INT NOT NULL,
                    position TEXT,
                    isFielded BOOLEAN,
                    CONSTRAINT player_uuid_pk PRIMARY KEY(uuid),
                    CONSTRAINT player_team_id_fk FOREING KEY (team_id)
                            REFERENCES team(id),
                    CONSTRAINT player_number_uk UNIQUE(number)
                );
                """;
    }
}
