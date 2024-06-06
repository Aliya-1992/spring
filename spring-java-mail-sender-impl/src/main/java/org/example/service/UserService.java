package org.example.service;

import org.example.repository.RegRepository;
import org.example.entity.RegUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class UserService {






    private RegRepository regRepository;

    @Autowired

    public UserService(RegRepository regRepository){
    this.regRepository = regRepository;
    }

    public void createUser(RegUser regUser){
        regRepository.save(regUser);
    }
}
