package com.anon.chat.connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping("/connect")
    public ResponseEntity<Object> connect(@RequestParam("connectingUser") String connectingUser){
        connectionService.connectUser(connectingUser);

        return ResponseEntity.ok().build();
    }
}
