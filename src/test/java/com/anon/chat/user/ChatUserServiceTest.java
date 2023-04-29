package com.anon.chat.user;

import com.anon.chat.connection.UserConnectionDto;
import com.anon.chat.connection.UserConnectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ChatUserServiceTest {

    private ChatUserRepository chatUserRepositoryMock;

    @BeforeEach
    public void prepareMocks(){
        chatUserRepositoryMock = Mockito.mock(ChatUserRepository.class);
        Mockito.when(chatUserRepositoryMock.save(Mockito.any())).thenReturn(new ChatUserDto());
    }

    @Test
    void shouldReturnUuidOnUserRegistering() {
        //given
        ChatUserService chatUserService = new ChatUserService(chatUserRepositoryMock);

        //when
        var newUser = chatUserService.registerUser();

        //then
        Assertions.assertTrue(newUser.matches(".{8}-.{4}-.{4}-.{4}-.{12}"));
    }

    @Test
    void shouldSaveToDbOnce() {
        //given
        ChatUserService chatUserService = new ChatUserService(chatUserRepositoryMock);

        //when
        var newUser = chatUserService.registerUser();

        //then
        Mockito.verify(chatUserRepositoryMock, Mockito.times(1)).save(Mockito.any());
    }
}