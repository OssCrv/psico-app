package com.psicotaller.psicoapp.backend.domain;

import com.psicotaller.psicoapp.backend.persistence.entities.Role;
import com.psicotaller.psicoapp.backend.persistence.entities.UserApp;
import com.psicotaller.psicoapp.backend.persistence.jpa.UserAppJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static com.psicotaller.psicoapp.backend.persistence.entities.Role.ADMIN;

@Service
public class UserAppService implements UserDetailsService {

    @Autowired
    private UserAppJpaRepository userAppJpaRepository;

    public UserApp getByUsername(String usernameApp){
        return userAppJpaRepository.findByUsername(usernameApp).orElse(new UserApp());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.empty();
        Optional<UserApp> userApp =  userAppJpaRepository.findByUsername(username);

        if(userApp.isPresent()){
            user = userApp.map(
                    userApp1 ->
                            new User(
                                    userApp1.getUsername(),
                                    userApp1.getPass(),
                                    Set.of(new Role(ADMIN)))
            );
        }

        return user.orElseThrow(
                () ->
                        new UsernameNotFoundException(
                                String.format("Username %s not found", username
                        )
                )
        );
    }
}
