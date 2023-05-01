package com.anon.chat.message;

import com.anon.chat.ChatMessageDto;
import com.anon.chat.SinkManager;
import com.anon.chat.connection.Connection;
import com.anon.chat.connection.ConnectionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

class MessageServiceTest {

    private SinkManager sinkManagerMock;
    private ConnectionManager connectionManagerMock;

    @BeforeEach
    public void prepare(){
        sinkManagerMock = Mockito.mock(SinkManager.class);
        Mockito.doNothing().when(sinkManagerMock).pushMessage(Mockito.any(), Mockito.any());

        connectionManagerMock = Mockito.mock(ConnectionManager.class);
        //Mockito.when(connectionManagerMock.getConnectionForUser("sender"))
        //        .thenReturn(new Connection("sender", "sender2"));
    }

    @Test
    void shouldCallPushMessageOnce() {
        //given
        MessageService messageService = new MessageService(sinkManagerMock, connectionManagerMock);

        //when
        messageService.send(new MessageDao("sender","message"));

        //then
        Mockito.verify(sinkManagerMock, Mockito.times(1)).pushMessage(Mockito.any(), Mockito.any());
    }

    @Test
    void shouldThrowNoUserFoundException() {
        //given
        MessageService messageService = new MessageService(sinkManagerMock, new ConnectionManager());

        //then
        Assertions.assertThrows(RuntimeException.class,
                () -> messageService.send(new MessageDao("sender", "message")));
    }

    @Test
    void shouldThrowRuntimeExceptionWhenConnectionNotFound() {
        //given
        MessageService messageService = new MessageService(sinkManagerMock, connectionManagerMock);

        //then
        Assertions.assertThrows(RuntimeException.class,
                () -> messageService.getSinkForUser("unknownSender"));
    }

    @Test
    void shouldContainMessageInTheSinkAfterSendOperation() {
        //given
        final String senderUsername = "HappyPathUser";
        final String receiverUsername = "HappyPathAddressee";

        final Connection usersConnection = new Connection(senderUsername, receiverUsername);

        Mockito.when(connectionManagerMock.getConnectionForUser(senderUsername))
                .thenReturn(usersConnection);

        SinkManager realSinkManager = new SinkManager();
        realSinkManager.makeSinkForConnection(usersConnection);

        MessageService messageService = new MessageService(realSinkManager, connectionManagerMock);

        //when
        var message = new ChatMessageDto(senderUsername, "Message1");
        messageService.send(new MessageDao(senderUsername, "Message1"));

        var sink = messageService.getSinkForUser(senderUsername);

        //then
        StepVerifier.create(sink.asFlux(), 1)
                .expectNext(message)
                .verifyComplete();

    }
}