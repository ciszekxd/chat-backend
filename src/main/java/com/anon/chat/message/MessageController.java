package com.anon.chat.message;

import com.anon.chat.ChatMessageDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Sinks;

import javax.validation.Valid;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public void sendMessage(@Valid MessageDao messageDao){
            messageService.send(messageDao);
    }

    @PostMapping("/stream/{user}")
    public Sinks.Many<ChatMessageDto> getSseStreamForUser(@PathVariable("user") String userId){
        return messageService.getSinkForUser(userId);
    }

}
