package com.anon.chat.connection;

import com.anon.chat.user.ChatUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class ConnectionServiceTest {

    private final List<String> userList = List.of("user1", "user2", "user3", "user4", "user5");

    private ChatUserRepository chatUserRepositoryMock;

    private UserConnectionRepository userConnectionRepositoryMock;

    @BeforeEach
    public void prepareMocks(){
        chatUserRepositoryMock = Mockito.mock(ChatUserRepository.class);
        Mockito.when(chatUserRepositoryMock.findAllUsers()).thenReturn(userList);

        userConnectionRepositoryMock = Mockito.mock(UserConnectionRepository.class);
        Mockito.when(userConnectionRepositoryMock.save(Mockito.any())).thenReturn(new UserConnectionDto());
    }

    @Test
    void shouldPerformTwoSaveActionsForConnection() {
        //given
        ConnectionService connectionService = new ConnectionService(chatUserRepositoryMock, userConnectionRepositoryMock);

        //when
        connectionService.connectUser("user1");

        //then
        Mockito.verify(userConnectionRepositoryMock, Mockito.times(2)).save(Mockito.any());
    }
}