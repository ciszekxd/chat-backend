package com.anon.chat.connection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class ConnectionManager {
    private final List<Connection> connectionList = new ArrayList<>();

    public void addConnection(Connection connection){
        connectionList.add(connection);
    }

    public Connection getConnectionForUser(final String username){
        return connectionList.stream()
                .filter(connection -> connection.isUserInConnection(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Connection for user: %s not found", username)));
    }
}
