package com.anon.chat.message;

import com.anon.chat.ChatMessageDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
public class SseController {

    private final MessageService messageService;

    public SseController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(path = "/stream/{user}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessageDto> getSseStreamForUser(@PathVariable("user") String userId){
        return messageService.getSinkForUser(userId);
    }
}
