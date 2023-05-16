package com.anon.chat.connection.event;

import com.anon.chat.connection.Connection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

public class ConnectionEvent extends ApplicationEvent {

    @Getter
    @Setter
    Connection connection;

    public ConnectionEvent(Connection connection, Object source) {
        super(source);
        this.connection = connection;
    }


}
