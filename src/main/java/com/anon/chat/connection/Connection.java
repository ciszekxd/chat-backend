package com.anon.chat.connection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@ToString
public class Connection {
    private final String firstUser;

    private final String secondUser;

    @Getter
    @Setter
    private Long id;

    public Connection(String firstUser, String secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    public String getConnectedUser(String user) throws UserNotFoundInConnection{
        if (!firstUser.equals(user) && !secondUser.equals(user)){
            throw new UserNotFoundInConnection(user + " not found");
        }

        if (firstUser.equals(user)){
            return secondUser;
        }

        return firstUser;
    }

    public boolean isUserInConnection(final String user) {
        return this.firstUser.equals(user) || this.secondUser.equals(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(firstUser, that.firstUser) && Objects.equals(secondUser, that.secondUser)
                || Objects.equals(firstUser, that.secondUser) && Objects.equals(secondUser, that.firstUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstUser, secondUser);
    }
}
