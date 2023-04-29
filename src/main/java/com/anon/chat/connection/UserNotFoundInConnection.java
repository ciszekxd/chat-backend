package com.anon.chat.connection;

public class UserNotFoundInConnection extends Exception{
    public UserNotFoundInConnection(String message) {
        super(message);
    }
}
