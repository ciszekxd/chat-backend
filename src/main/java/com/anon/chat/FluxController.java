package com.anon.chat;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Controller
public class FluxController {

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessageDto> getSse(){
        Sinks.Many<ChatMessageDto> sink = SinkManager.getSink();

        var user = "user1";
        var message = 1L;

        sink.tryEmitNext(new ChatMessageDto(user, String.valueOf(message)));

        return sink.asFlux();
    }

    @GetMapping("/add/{message}")
    public void pushSse(@PathVariable("message") String message){
        SinkManager.pushMessage(message);
    }
}
