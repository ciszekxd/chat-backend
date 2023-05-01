package com.anon.chat;

import com.anon.chat.connection.Connection;
import com.anon.chat.message.MessageDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Component
@Scope("singleton")
public class SinkManager {
    private static Sinks.Many<ChatMessageDto> sinkMap = null;

    private final Map<Connection, Sinks.Many<ChatMessageDto>> connectionMap = new HashMap<>();

    public Sinks.Many<ChatMessageDto> getSink(){
        if (sinkMap == null){
            sinkMap = Sinks.many().multicast().onBackpressureBuffer();
        }
        return sinkMap;
    }

    public void makeSinkForConnection(final Connection connection){
        connectionMap.put(connection, Sinks.many().multicast().onBackpressureBuffer(100));
    }

    public void pushMessage(Connection connection, MessageDao messageDao){
        var connectionSink = connectionMap.get(connection);
        connectionSink.tryEmitNext(new ChatMessageDto(messageDao.getSender(), messageDao.getMessage()));
    }

    public Optional<Sinks.Many<ChatMessageDto>> getSinkForConnection(Connection connection){
        if (connectionMap.containsKey(connection)){
            return  Optional.of(connectionMap.get(connection));
        }
        
        return Optional.empty();
    }
}
