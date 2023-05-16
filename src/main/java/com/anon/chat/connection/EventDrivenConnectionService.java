package com.anon.chat.connection;

import com.anon.chat.connection.event.ConnectionEvent;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Primary
@Service
public class EventDrivenConnectionService implements ConnectionService, ApplicationListener<ConnectionEvent> {

    final  UserQueue userQueue;

    private String userRunningEndpoint;
    private Connection connectionFromEvent;
    private final UserConnectionRepository userConnectionRepository;


    private final Logger logger = LoggerFactory.getLogger(EventDrivenConnectionService.class);

    public EventDrivenConnectionService(UserQueue userQueue, UserConnectionRepository userConnectionRepository) {
        this.userQueue = userQueue;
        this.userConnectionRepository = userConnectionRepository;
    }

    @Async
    @Override
    public void onApplicationEvent(ConnectionEvent event) {
        if (event.getConnection().isUserInConnection(userRunningEndpoint)){
            synchronized (this) {
                logger.info(this.toString());
                connectionFromEvent = event.getConnection();
                notify();
            }
            logger.info("NOTIFY INVOKED");
        }
    }

    @Override
    public void connectUser(String connectingUser) {
        userQueue.addToQueue(connectingUser);
        userRunningEndpoint = connectingUser;

        var optionalConnection = userQueue.tryToMakeNewConnection();

        Connection connection = optionalConnection.orElseGet(() -> {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return connectionFromEvent;
        });

        logger.info(connection.toString());
        new Object();

    }

    @Override
    public void closeConnectionForUser(String username) {

    }
}
