package org.example.persistence.migrations;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import static org.example.persistence.config.ConnectionConfig.getConnection;

public class MigrationStrategy {

    public void executeMigration() {
        var originalOut = System.out;
        var originalErr = System.err;

        try (var fos = new FileOutputStream("liquibase.log");
             var printStream = new PrintStream(fos)) {

            System.setOut(printStream);
            System.setErr(printStream);

            try (Connection connection = getConnection();
                 JdbcConnection jdbcConnection = new JdbcConnection(connection);
                 Liquibase liquibase = new Liquibase(
                         "/db/changelog/db.changelog-master.yml",
                         new ClassLoaderResourceAccessor(),
                         jdbcConnection)) {

                liquibase.update();

            } catch (SQLException | LiquibaseException e) {
                e.printStackTrace();
                System.setErr(originalErr);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }
    }
}
