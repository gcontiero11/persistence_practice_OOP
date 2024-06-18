package dev.gustavo.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseBuilder {

    public void cleanDataBase() {
        deletePreviousDataBase();
        createNewDataBase();
        System.out.println("DataBase Created");
    }

//    public void createDatabaseIfIsMissing() {
//        if (isDataBaseMissing()) {
//            createNewDataBase();
//            System.out.println("DataBase Created");
//        }
//    }

    private boolean isDataBaseMissing() {
        return !Files.exists(Paths.get("Database.db"));
    }

    private void createNewDataBase() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(playersTableSql());
            statement.addBatch(teamsTableSql());
            statement.addBatch("INSERT INTO team(id,name,base_location,captain_id,coach_name)" +
                               "values(-1,'Time dos Desempregados','null',NULL,'null');");
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
                CREATE TABLE team(
                    id INT NOT NULL,
                    name TEXT NOT NULL,
                    base_location TEXT,
                    captain_id UUID,
                    coach_name TEXT,
                    CONSTRAINT team_id_pk PRIMARY KEY(id),
                    CONSTRAINT captain_id_fk FOREIGN KEY (captain_id) REFERENCES player(uuid)
                );
                """;
    }

    private String playersTableSql() {
        return """
                CREATE TABLE player(
                    uuid UUID NOT NULL,
                    team_id INTEGER,
                    name TEXT NOT NULL,
                    number INT NOT NULL,
                    position TEXT,
                    is_fielded BOOLEAN,
                    CONSTRAINT player_uuid_pk PRIMARY KEY(uuid),
                    CONSTRAINT player_team_id_fk FOREIGN KEY (team_id)
                            REFERENCES team(id),
                    CONSTRAINT player_number_uk UNIQUE(number)
                );
                """;
    }
}
