package com.anon.chat.message;

import com.anon.chat.ChatMessageDto;
import com.anon.chat.SinkManager;
import com.anon.chat.connection.ConnectionManager;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class MessageService {

    private final SinkManager sinkManager;
    private final ConnectionManager connectionManager;

    public MessageService(SinkManager sinkManager, ConnectionManager connectionManager) {
        this.sinkManager = sinkManager;
        this.connectionManager = connectionManager;
    }

    public void send(final MessageDao messageDao) {
        var sender = messageDao.getSender();
        var connection = connectionManager.getConnectionForUser(sender);

        sinkManager.pushMessage(connection, messageDao);
    }

    public Sinks.Many<ChatMessageDto> getSinkForUser(final String user){
        var connection = connectionManager.getConnectionForUser(user);
        return sinkManager.getSinkForConnection(connection)
                .orElseThrow(() -> new RuntimeException("connection not found"));
    }

}
