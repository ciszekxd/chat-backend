package com.anon.chat.connection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class ConnectionManager {
    private final List<Connection> connectionList = new ArrayList<>();

    private final ConnectionRepository connectionRepository;

    public ConnectionManager(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public void addConnection(Connection connection){
        connectionList.add(connection);

        var connectionDto = new ConnectionDto(null, LocalDateTime.now(), true);
        connectionRepository.save(connectionDto);

        connection.setId(connectionDto.getId());
    }

    public void removeConnection(Connection connection){
        connectionList.remove(connection);
        connectionRepository.setInactive(connection.getId());
    }

    public Connection getConnectionForUser(final String username){
        return connectionList.stream()
                .filter(connection -> connection.isUserInConnection(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Connection for user: %s not found", username)));
    }
}
