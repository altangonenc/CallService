package com.Altan.CallService.controller;

import com.Altan.CallService.domain.User;
import com.Altan.CallService.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
@Api(value = "Users documentation")
public class UserController {

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;


    @GetMapping
    @ApiOperation(value = "User list method")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    @ApiOperation(value = "The method of adding new user")
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    @ApiOperation(value = "Method to delete existing user")
    public void deleteUser(@PathVariable("userId")Long userId ){
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    @ApiOperation(value = "Method to edit existing user")
    public void updateUser(
            @PathVariable("userId")Long userId,
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String phone){
        userService.updateUser(userId,name,phone);
    }

}
