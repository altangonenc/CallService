package com.Altan.CallService.service;

import com.Altan.CallService.domain.OnlineUser;
import com.Altan.CallService.domain.User;
import com.Altan.CallService.repository.OnlineUserRepository;
import com.Altan.CallService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OnlineUserService {
    private final OnlineUserRepository onlineUserRepository;
    private final UserRepository userRepository;

    @Autowired
    public OnlineUserService(OnlineUserRepository onlineUserRepository, UserRepository userRepository) {
        this.onlineUserRepository = onlineUserRepository;
        this.userRepository = userRepository;
    }

    public void logInNewUser(String phoneNumber){
        if (userRepository.findUserByPhone(phoneNumber) != null){
            Optional<User> tmpUser = userRepository.findUserByPhone(phoneNumber);
            Long a = tmpUser.get().getId();
            User user = userRepository.findUserById(a);
            OnlineUser onlineUser = new OnlineUser(user);
            onlineUserRepository.save(onlineUser);
        }
    }

    public List<OnlineUser> getOnlineUsers(){
        return onlineUserRepository.findAll();
    }
}
