package com.anon.chat;

import reactor.core.publisher.Sinks;

public class SinkManager {
    private static Sinks.Many<ChatMessageDto> sinkMap = null;

    public static Sinks.Many<ChatMessageDto> getSink(){
        if (sinkMap == null){
            sinkMap = Sinks.many().multicast().onBackpressureBuffer();
        }
        return sinkMap;
    }

    public static void pushMessage(String message){
        if (sinkMap != null) {
            sinkMap.tryEmitNext(new ChatMessageDto("placeHolderUser", message));
        }
    }


}
