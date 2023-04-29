package com.anon.chat.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ChatUserService {
    private final ChatUserRepository chatUserRepository;

    public ChatUserService(ChatUserRepository userRepository) {
        this.chatUserRepository = userRepository;
    }

    public String registerUser(){
        UUID uuid = UUID.randomUUID();

        var user = new ChatUserDto(uuid.toString(), LocalDateTime.now());

        chatUserRepository.save(user);

        return user.getUserHash();
    }
}
