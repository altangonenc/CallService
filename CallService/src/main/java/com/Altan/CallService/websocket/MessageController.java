package com.Altan.CallService.websocket;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;



@Controller
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/hello")
    @SendToUser("/queue/reply")

        public String userNotification (String userPhone){
            return messageService.notification(userPhone);
        }



}