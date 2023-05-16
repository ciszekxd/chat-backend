package com.anon.chat.connection;

public interface ConnectionService {
    void connectUser(final String connectingUser);
    void closeConnectionForUser(final String username);


}
