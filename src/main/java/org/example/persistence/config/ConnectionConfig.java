package org.example.persistence.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConnectionConfig {

    public static Connection getConnection() throws SQLException {
        var uri = "jdbc:mysql://localhost/board";
        var connection = DriverManager.getConnection(uri, "root", "root" );
        connection.setAutoCommit(false);
        return connection;
    }
}
