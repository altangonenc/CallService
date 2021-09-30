package com.Altan.CallService.repository;

import com.Altan.CallService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //@Query("SELECT s FROM User s WHERE s.phone=?1")
    Optional<User> findUserByPhone(String phone);
}

