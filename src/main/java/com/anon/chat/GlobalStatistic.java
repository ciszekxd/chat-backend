package com.anon.chat;

import com.anon.chat.user.ChatUserRepository;
import org.springframework.stereotype.Service;

@Service
public class GlobalStatistic {

    private static ChatUserRepository chatUserRepository;

    private static Integer allUsersNumber = null;

    public GlobalStatistic(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    public static Integer getAllUsersNumber(){
        if(allUsersNumber == null){
            allUsersNumber = chatUserRepository.findAmountOfAll();
        }

        return allUsersNumber;
    }


}
