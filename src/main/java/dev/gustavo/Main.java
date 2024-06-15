package dev.gustavo;

import dev.gustavo.persistence.DataBaseBuilder;

public class Main {
    public static void main(String[] args) {
        DataBaseBuilder dbBuilder = new DataBaseBuilder();
        dbBuilder.createDatabaseIfIsMissing();
    }
}