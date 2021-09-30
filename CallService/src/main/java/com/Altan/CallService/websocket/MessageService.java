package com.Altan.CallService.websocket;

import com.Altan.CallService.domain.Call;
import com.Altan.CallService.repository.CallRepository;
import com.Altan.CallService.repository.UserRepository;
import com.Altan.CallService.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final UserRepository userRepository;
    private final CallRepository callRepository;
    private final CallService callService;

    @Autowired
    public MessageService(CallRepository callRepository, UserRepository userRepository, CallService callService){
        this.callRepository = callRepository;
        this.userRepository = userRepository;
        this.callService = callService;
    }

    public List<Call> getCallHistory(String userPhone){
        return callRepository.findCallsByCalledPhone(userPhone);
    }

    public Call notification(){
        Call tmpCall = new Call();
        if (callService.getCallFlag() == true){
            tmpCall = callService.getLastCalledPhoneNumber();
            callService.setCallFlag(false);
        }
        return tmpCall;



    }



}

