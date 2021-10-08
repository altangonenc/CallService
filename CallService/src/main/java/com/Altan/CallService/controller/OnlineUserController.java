package com.Altan.CallService.controller;

import com.Altan.CallService.domain.OnlineUser;
import com.Altan.CallService.service.OnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/online")
public class OnlineUserController {
    @Autowired
    public OnlineUserController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }
    private final OnlineUserService onlineUserService;

    @PostMapping(path = "{phoneNumber}")
    public void logInNewUser(@PathVariable("phoneNumber")String phoneNumber){
        onlineUserService.logInNewUser(phoneNumber);
    }

    @GetMapping
    public List<OnlineUser> getOnlineUsers(){
        return onlineUserService.getOnlineUsers();
    }

}
