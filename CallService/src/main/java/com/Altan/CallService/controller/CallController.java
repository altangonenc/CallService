package com.Altan.CallService.controller;

import com.Altan.CallService.domain.Call;
import com.Altan.CallService.service.CallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/call")
@Api(value = "Calls documentation")
public class CallController {

    private final CallService callService;

    @Autowired
    public CallController(CallService callService){
        this.callService = callService ;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('call:read')")
    @ApiOperation(value = "Call list method")
    public List<Call> getCalls(){
        return callService.getCalls();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('call:write')")
    @ApiOperation(value = "New Call adding method")
    public void makeNewCall(@RequestBody Call call) throws InterruptedException {
        callService.newCall(call);
    }


}
