package com.Altan.CallService.websocket;

import com.Altan.CallService.domain.Call;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/hello")
    @SendToUser("/queue/reply")
    public List<Call> getCallHistory(String username) {
        return messageService.getCallHistory(username);
    }



}