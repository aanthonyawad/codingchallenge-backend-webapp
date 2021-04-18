package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.config.security.CustomUserDetails;
import de.iplytics.codingchallenge_backend_webapp.model.User;
import de.iplytics.codingchallenge_backend_webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    MyUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Iterable<User> iterable = userRepository.findAll();
        User user = null;
        Iterator<User> it = iterable.iterator();
        while (it.hasNext()){
            User temp = it.next();
            if(temp.getEmail().equals(email))
                user = temp;
        }
        
        if(user == null)throw new UsernameNotFoundException("Email not Found");
        return new CustomUserDetails(user);
    }
}
