package com.Altan.CallService.repository;

import com.Altan.CallService.domain.OnlineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineUserRepository extends JpaRepository<OnlineUser,String> {

}
