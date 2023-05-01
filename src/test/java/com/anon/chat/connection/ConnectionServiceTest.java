package com.anon.chat.connection;

import com.anon.chat.SinkManager;
import com.anon.chat.user.ChatUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class ConnectionServiceTest {

    private final List<String> userList = List.of("user1", "user2", "user3", "user4", "user5");

    private ChatUserRepository chatUserRepositoryMock;

    private UserConnectionRepository userConnectionRepositoryMock;
    private ConnectionManager connectionManager;
    private SinkManager sinkManager;

    @BeforeEach
    public void prepareMocks(){
        chatUserRepositoryMock = Mockito.mock(ChatUserRepository.class);
        Mockito.when(chatUserRepositoryMock.findAllUsers()).thenReturn(userList);

        userConnectionRepositoryMock = Mockito.mock(UserConnectionRepository.class);
        Mockito.when(userConnectionRepositoryMock.save(Mockito.any())).thenReturn(new UserConnectionDto());

        connectionManager = Mockito.mock(ConnectionManager.class);
        Mockito.doNothing().when(connectionManager).addConnection(Mockito.any());

        sinkManager = Mockito.mock(SinkManager.class);
        Mockito.doNothing().when(sinkManager).makeSinkForConnection(Mockito.any());
    }

    @Test
    void shouldPerformTwoSaveActionsForConnection() {
        //given
        ConnectionService connectionService = new ConnectionService(chatUserRepositoryMock,
                userConnectionRepositoryMock,
                sinkManager,
                connectionManager);

        //when
        connectionService.connectUser("user1");

        //then
        Mockito.verify(userConnectionRepositoryMock, Mockito.times(2)).save(Mockito.any());
    }
}