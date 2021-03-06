package com.Altan.CallService.service;

import com.Altan.CallService.domain.Call;
import com.Altan.CallService.domain.User;
import com.Altan.CallService.repository.CallRepository;
import com.Altan.CallService.repository.OnlineUserRepository;
import com.Altan.CallService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CallService {
    private final UserRepository userRepository;
    private final CallRepository callRepository;
    private final OnlineUserRepository onlineUserRepository;
    private Boolean callFlag=false;
    private Call lastCalledPhoneNumber;

    public Boolean getCallFlag() {
        return callFlag;
    }

    public void setCallFlag(Boolean callFlag) {
        this.callFlag = callFlag;
    }

    public Call getLastCalledPhoneNumber() {
        return lastCalledPhoneNumber;
    }

    public void setLastCalledPhoneNumber(Call lastCalledPhoneNumber) {
        this.lastCalledPhoneNumber = lastCalledPhoneNumber;
    }

    @Autowired
    public CallService(CallRepository callRepository, UserRepository userRepository, OnlineUserRepository onlineUserRepository) {     //UserRepository constructor
        this.callRepository = callRepository;
        this.userRepository = userRepository;                                               //Autowired yapılmalı
        this.onlineUserRepository = onlineUserRepository;
    }

    public List<Call> getCalls(){
        return callRepository.findAll();
    }



    public void newCall(Call call) throws InterruptedException {                     //Eger userda kayıtlı degilse arama yapamaz.

        LocalDateTime now = LocalDateTime.now();
        Optional<User> callerOptional = userRepository.findUserByPhone(call.getCallerPhone());
        Optional<User> calledOptional = userRepository.findUserByPhone(call.getCalledPhone());
        if((!callerOptional.isPresent()||!calledOptional.isPresent()) || !onlineUserRepository.existsByUser(callerOptional)){
            throw new IllegalStateException("Caller and called phone number should register first. ");
        }
        else {
             if (onlineUserRepository.existsByUser(userRepository.findUserByPhone(call.getCalledPhone()))) {
                 call.setDate(now);
                 callRepository.save(call);
                 setCallFlag(true);
                 Thread.sleep(7000);
                 setCallFlag(false);
                 setLastCalledPhoneNumber(call);
             }
             else{
                 call.setDate(now);
                 callRepository.save(call);
                 setCallFlag(false);
                 setLastCalledPhoneNumber(call);
                 System.out.println("The person you have called can not be reached at the moment please try later");
             }
        }
    }


}
