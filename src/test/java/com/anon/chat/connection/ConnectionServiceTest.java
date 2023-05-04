package com.anon.chat.connection;

import com.anon.chat.SinkManager;
import com.anon.chat.user.ChatUserDto;
import com.anon.chat.user.ChatUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
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
        Mockito.when(chatUserRepositoryMock.findChatUserDtoInUserHash(Mockito.any())).thenReturn(List.of(
                new ChatUserDto(1L, "83b85158-446b-4d8f-b3cb-f99b77291553", LocalDateTime.now()),
                new ChatUserDto(2L,"83b85158-446b-4d8f-b3cb-f99b77291554", LocalDateTime.now())));

        userConnectionRepositoryMock = Mockito.mock(UserConnectionRepository.class);
        Mockito.when(userConnectionRepositoryMock.save(Mockito.any())).thenReturn(new UserConnectionDto());

        connectionManager = Mockito.mock(ConnectionManager.class);
        Mockito.when(connectionManager.addConnection(Mockito.any())).thenReturn(1L);

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