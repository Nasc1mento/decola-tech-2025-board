package org.example;

import org.example.persistence.migrations.MigrationStrategy;
import org.example.ui.MainMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        new MigrationStrategy().executeMigration();
        new MainMenu().execute();
    }
}