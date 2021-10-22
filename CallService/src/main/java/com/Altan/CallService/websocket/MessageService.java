package com.Altan.CallService.websocket;

import com.Altan.CallService.domain.Call;
import com.Altan.CallService.repository.CallRepository;
import com.Altan.CallService.repository.OnlineUserRepository;
import com.Altan.CallService.repository.UserRepository;
import com.Altan.CallService.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class MessageService {
    private final UserRepository userRepository;
    private final CallRepository callRepository;
    private final CallService callService;
    private final OnlineUserRepository onlineUserRepository;


    @Autowired
    public MessageService(CallRepository callRepository, UserRepository userRepository, CallService callService, OnlineUserRepository onlineUserRepository){
        this.callRepository = callRepository;
        this.userRepository = userRepository;
        this.callService = callService;

        this.onlineUserRepository = onlineUserRepository;
    }

    public String notification(String userPhone){
        Call tmpCall = new Call();
        String message="no one is calling.";
        if (userRepository.existsByPhone(userPhone)) {
            if (onlineUserRepository.existsByUser(userRepository.findUserByPhone(userPhone))) {
                if (callService.getCallFlag() == true) {
                    tmpCall = callService.getLastCalledPhoneNumber();

                }
                if (tmpCall.getCallerPhone() == null) {
                    Call call = new Call();
                    ArrayList<Call> tmpList = callRepository.findCallsByCalledPhoneAndIsSeen(userPhone, false);
                    ArrayList<Call> finalList = new ArrayList<Call>();
                    for (int i = 0; i < tmpList.size(); i++) {
                        try {
                            if (false == tmpList.get(i).getSeen()) {
                                call = tmpList.get(i);
                                finalList.add(call);
                                callRepository.delete(call);
                                call.setSeen(true);
                                callRepository.save(call);
                            } else
                                finalList = null;
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerException Caught");
                        }
                    }
                    return finalList.toString();

                } else
                    return tmpCall.getCallerPhone() + " is calling you ";
            } else
                return "you must be online first.";
        } else
            return "";

    }

}

