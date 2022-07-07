package com.example.manage;

import com.example.manage.Entity.User;
import com.example.manage.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ManageApplication {

    public static void main(String[] args)
    {
        ApplicationContext applicationContext = SpringApplication.run(ManageApplication.class, args);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);
        /*User user = new User();
        user.setUsername("Traistorm");
        user.setPassword(passwordEncoder.encode("mancity1st"));
        userRepository.save(user);
        System.out.println(user);*/

    }





}
