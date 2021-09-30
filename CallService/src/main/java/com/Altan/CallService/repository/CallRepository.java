package com.Altan.CallService.repository;

import com.Altan.CallService.domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,String> {
    List<Call> findCallsByCalledPhone(String calledPhone);
    List<Call> getCallById(Long id);
    Call findCallByCalledPhone(String userPhone);
}
