package com.Altan.CallService.configuration;

import com.Altan.CallService.domain.User;
import com.Altan.CallService.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){

        return args -> {
            User Necdet = new User(
                    "Necdet", "05368193453"
            );
            User Altan = new User(
                    "Altan", "05348525901"
            );
            userRepository.saveAll(
                    List.of(Necdet,Altan)
            );
        };
    }
}