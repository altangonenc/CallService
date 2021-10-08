package com.Altan.CallService.service;

import com.Altan.CallService.domain.Call;
import com.Altan.CallService.domain.User;
import com.Altan.CallService.repository.CallRepository;
import com.Altan.CallService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Timer;

@Service
public class CallService {
    private final UserRepository userRepository;
    private final CallRepository callRepository;
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
    public CallService(CallRepository callRepository, UserRepository userRepository) {     //UserRepository constructor
        this.callRepository = callRepository;
        this.userRepository = userRepository;                                               //Autowired yapılmalı
    }

    public List<Call> getCalls(){
        return callRepository.findAll();
    }



    public void newCall(Call call) throws InterruptedException {                     //Eger userda kayıtlı degilse arama yapamaz.

        LocalDateTime now = LocalDateTime.now();
        Optional<User> userOptional = userRepository.findUserByPhone(call.getCallerPhone());
        Optional<User> calledOptional = userRepository.findUserByPhone(call.getCalledPhone());
        if(!userOptional.isPresent()||!calledOptional.isPresent()){
            throw new IllegalStateException("Caller and called phone number should register first. ");
        }
        else {
            call.setDate(now);
            callRepository.save(call);
            setCallFlag(true);
            Thread.sleep(7000);
            setCallFlag(false);
            setLastCalledPhoneNumber(call);
        }
    }


}
