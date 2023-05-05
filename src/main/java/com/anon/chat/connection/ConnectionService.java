package com.anon.chat.connection;

import com.anon.chat.SinkManager;
import com.anon.chat.user.ChatUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

@Service
public class ConnectionService {
    private final ChatUserRepository chatUserRepository;

    private final UserConnectionRepository userConnectionRepository;

    private final SinkManager sinkManager;
    private final ConnectionManager connectionManager;


    public ConnectionService(ChatUserRepository chatUserRepository,
                             UserConnectionRepository userConnectionRepository,
                             SinkManager sinkManager,
                             ConnectionManager connectionManager) {
        this.chatUserRepository = chatUserRepository;
        this.userConnectionRepository = userConnectionRepository;
        this.sinkManager = sinkManager;
        this.connectionManager = connectionManager;
    }

    public void connectUser(final String connectingUser){
        final var otherUser = findRandomUser(connectingUser);

        final var newConnection = new Connection(connectingUser, otherUser);

        sinkManager.makeSinkForConnection(newConnection);

        connectionManager.addConnection(newConnection);

        var chatUserDto = chatUserRepository.findChatUserDtoInUserHash(List.of(connectingUser, otherUser));

        addUserConnectionToDb(newConnection.getId(), chatUserDto.get(0).getId());
        addUserConnectionToDb(newConnection.getId(), chatUserDto.get(1).getId());
    }

    public void closeConnectionForUser(final String username){
        Connection connectionToClose = connectionManager.getConnectionForUser(username);

        sinkManager.closeSink(connectionToClose);
        connectionManager.removeConnection(connectionToClose);
    }

    private void addUserConnectionToDb(Long connectionId, Long userId){
        UserConnectionDto userConnectionDto = new UserConnectionDto(null, connectionId, userId);
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
