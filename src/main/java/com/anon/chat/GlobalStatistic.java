package com.anon.chat;

import com.anon.chat.user.ChatUserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class GlobalStatistic {

    private final ChatUserRepository chatUserRepository;

    private Integer allUsersNumber = null;

    public GlobalStatistic(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    public Integer getAllUsersNumber(){
        if(allUsersNumber == null){
            allUsersNumber = chatUserRepository.findAmountOfAll();
        }

        return allUsersNumber;
    }


}
