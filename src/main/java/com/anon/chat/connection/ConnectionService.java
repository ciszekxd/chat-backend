package com.anon.chat.connection;

import com.anon.chat.user.ChatUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.random.RandomGenerator;

@Service
public class ConnectionService {
    private final ChatUserRepository chatUserRepository;

    private final UserConnectionRepository userConnectionRepository;

    public ConnectionService(ChatUserRepository chatUserRepository, UserConnectionRepository userConnectionRepository) {
        this.chatUserRepository = chatUserRepository;
        this.userConnectionRepository = userConnectionRepository;
    }

    public void connectUser(final String connectingUser){
        final var otherUser = findRandomUser(connectingUser);

        ConnectionManager.addConnection(new Connection(connectingUser, otherUser));

        addConnectionToDb(connectingUser);
        addConnectionToDb(otherUser);

    }

    private void addConnectionToDb(final String userName){
        UserConnectionDto userConnectionDto = new UserConnectionDto(null, userName, LocalDateTime.now());
        userConnectionRepository.save(userConnectionDto);
    }

    private String findRandomUser(String excludedUser){
        var userHashList = new ArrayList<>(chatUserRepository.findAllUsers());

        userHashList.remove(excludedUser);

        var randomGenerator = RandomGenerator.getDefault();
        var randomIndex = randomGenerator.nextInt(0, userHashList.size());

        return userHashList.get(randomIndex);
    }
}
