package com.anon.chat.connection;

import com.anon.chat.user.ChatUserRepository;
import org.springframework.stereotype.Service;

import java.util.random.RandomGenerator;

@Service
public class ConnectionService {
    private final ChatUserRepository chatUserRepository;

    public ConnectionService(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    public final void connectUser(String connectingUser){
        var otherUser = findRandomUser(connectingUser);

        ConnectionManager.addConnection(new Connection(connectingUser, otherUser));
    }

    private String findRandomUser(String excludedUser){
        var userHashList = chatUserRepository.findAllUsers();

        userHashList.remove(excludedUser);

        var randomGenerator = RandomGenerator.getDefault();
        var randomIndex = randomGenerator.nextInt(0, userHashList.size());

        return userHashList.get(randomIndex);
    }
}
