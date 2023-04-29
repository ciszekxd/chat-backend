package com.anon.chat.connection;

import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    private static final List<Connection> connectionList = new ArrayList<>();

    public static void addConnection(Connection connection){
        connectionList.add(connection);
    }
}
