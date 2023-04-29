package com.anon.chat.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ChatUserService {
    private final ChatUserRepository userRepository;

    public ChatUserService(ChatUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(){
        UUID uuid = UUID.randomUUID();

        var user = new ChatUserDto(uuid.toString(), LocalDateTime.now());

        userRepository.save(user);

        return user.getUserHash();
    }
}
