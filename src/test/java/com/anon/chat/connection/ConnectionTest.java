package com.anon.chat.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConnectionTest {

    @Test
    void shouldReturnUser2AsConnectionOfUser1() throws UserNotFoundInConnection {
        //given
        var user1 = "user1";
        var user2 = "user2";
        Connection connection = new Connection(user1, user2);

        //when
        var user1Connection = connection.getConnectedUser(user1);

        //then
        Assertions.assertEquals(user2, user1Connection);
    }

    @Test
    void shouldReturnUser1AsConnectionOfUser2() throws UserNotFoundInConnection {
        //given
        var user1 = "user1";
        var user2 = "user2";
        Connection connection = new Connection(user1, user2);

        //when
        var user2Connection = connection.getConnectedUser(user2);

        //then
        Assertions.assertEquals(user1, user2Connection);
    }

    @Test
    void shouldThrowNoUserFound() {
        //given
        var user1 = "user1";
        var user2 = "user2";
        Connection connection = new Connection(user1, user2);

        //then
        Assertions.assertThrows(UserNotFoundInConnection.class, () -> connection.getConnectedUser("unknownUser"));
    }

    @Test
    void shouldConsiderTwoConnectionsAsEqual() {
        //given
        var user1 = "user1";
        var user2 = "user2";
        Connection connectionOne = new Connection(user1, user2);
        Connection connectionTwo = new Connection(user2, user1);

        //then
        Assertions.assertEquals(connectionOne, connectionTwo);
    }

    @Test
    void shouldConsiderTwoConnectionsHashesAsNotEqual() {
        //given
        var user1 = "user1";
        var user2 = "user2";
        Connection connectionOne = new Connection(user1, user2);
        Connection connectionTwo = new Connection(user2, user1);

        //then
        Assertions.assertNotEquals(connectionOne.hashCode(), connectionTwo.hashCode());
    }
}