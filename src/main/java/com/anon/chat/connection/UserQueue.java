package com.anon.chat.connection;

import com.anon.chat.connection.event.ConnectionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

@Component
@Scope("singleton")
public class UserQueue {
    private final Queue<String> userQueue = new ArrayDeque<>();
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserQueue(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public synchronized void addToQueue(String user){
            userQueue.add(user);
    }

    public synchronized Optional<Connection> tryToMakeNewConnection(){
        if (this.userQueue.size() % 2 == 0 && this.userQueue.size() > 0) {
            var userOne = userQueue.poll();
            var userTwo = userQueue.poll();

            var connection = new Connection(userOne, userTwo);
            var connectionEvent = new ConnectionEvent(connection, this);

            applicationEventPublisher.publishEvent(connectionEvent);
            return Optional.of(connection);
        }
        return Optional.empty();
    }
}
