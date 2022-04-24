package com.psicotaller.psicoapp.backend.domain;

import com.psicotaller.psicoapp.backend.persistence.entities.UserApp;
import com.psicotaller.psicoapp.backend.persistence.jpa.UserAppJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAppService {

    @Autowired
    private UserAppJpaRepository userAppJpaRepository;

    public UserApp getByUsername(String usernameApp){
        return userAppJpaRepository.findByUsername(usernameApp).orElse(new UserApp());
    }

}
