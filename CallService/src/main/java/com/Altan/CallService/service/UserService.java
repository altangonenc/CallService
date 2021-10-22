package com.Altan.CallService.service;

import com.Altan.CallService.domain.User;
import com.Altan.CallService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {     //UserRepository constructor
        this.userRepository = userRepository;               //Autowired yapılmalı
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository
                .findUserByPhone(user.getPhone());
        if(userOptional.isPresent()){
            throw new IllegalStateException("phone number taken.");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("user with id "+userId+" does not exists.");
        }
        userRepository.deleteById(userId);
    }
    @Transactional
    public void updateUser(Long userId, String name, String phone) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " +
                userId + " does not exist."));
        if (name!=null  && name.length()>0  && name != user.getName()){
            user.setName(name);
        }
        if (phone!=null  && phone.length()>0  &&  phone != user.getPhone()){
            Optional<User>userOptional = userRepository.findUserByPhone(phone);
            if (userOptional.isPresent()){
                throw new IllegalStateException("phone number taken.");
            }
            user.setPhone(phone);
        }

    }
}
