package dev.gustavo.persistence;

import java.sql.PreparedStatement;

public class DataBaseBuilder {

    public static void createDatabase(){
        deletePreviousDataBase();
        createNewDataBase();
    }

    private static void createNewDataBase() {
        StringBuilder

        try(PreparedStatement statement = ConnectionFactory.createPrepareStatement()){

        }
    }

    private static void deletePreviousDataBase() {
    }

    private String playersTableSql(){
        return """
                CREATE TABLE player IF NOT EXISTS(
                    uuid UUID NOT NULL,
                    name TEXT NOT NULL,
                    number INT NOT NULL,
                    
                );
                """
    }
}
