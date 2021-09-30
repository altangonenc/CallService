package com.Altan.CallService.controller;

import com.Altan.CallService.domain.Call;
import com.Altan.CallService.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/call")
public class CallController {
    private final CallService callService;
    @Autowired
    public CallController(CallService callService){
        this.callService = callService ;
    }
    @GetMapping
    public List<Call> getCalls(){
        return callService.getCalls();
    }
    @PostMapping
    public void makeNewCall(@RequestBody Call call){
        callService.newCall(call);
    }


}
