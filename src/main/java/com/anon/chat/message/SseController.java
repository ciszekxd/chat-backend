package com.anon.chat.message;

import com.anon.chat.ChatMessageDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;


@Controller
public class SseController {

    private final MessageService messageService;

    public SseController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/stream/{user}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessageDto> getSseStreamForUser(@PathVariable("user") String userId){
        return messageService.getSinkForUser(userId);
    }
}
