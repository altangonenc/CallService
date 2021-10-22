package com.Altan.CallService.repository;

import com.Altan.CallService.domain.OnlineUser;
import com.Altan.CallService.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OnlineUserRepository extends JpaRepository<OnlineUser,String> {
    boolean existsByUser(Optional<User> user);
    OnlineUser findOnlineUserByUser_Phone(String phone);

    void deleteById(Long s);
}
