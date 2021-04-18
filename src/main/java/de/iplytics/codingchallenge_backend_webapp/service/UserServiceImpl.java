package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.AuthenticationRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.CreateUserRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.AuthenticationResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.UserService;
import de.iplytics.codingchallenge_backend_webapp.model.User;
import de.iplytics.codingchallenge_backend_webapp.repository.UserRepository;
import de.iplytics.codingchallenge_backend_webapp.service.JwtService;
import de.iplytics.codingchallenge_backend_webapp.service.MyUserDetailsService;
import de.iplytics.codingchallenge_backend_webapp.util.StringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    AuthenticationManager authenticationManager;

    MyUserDetailsService userDetailsService;


    UserRepository userRepository;

    JwtService jwtService;

    @Autowired
    UserServiceImpl(AuthenticationManager authenticationManager
            ,MyUserDetailsService userDetailsService
            ,UserRepository userRepository
            ,JwtService jwtService){

        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponse signUp(CreateUserRequest createUserRequest) {
        //check if user exists
        UserDetails userDetails;
        try{
            UserDetails u = userDetailsService.loadUserByUsername(createUserRequest.getUsername());
            // if found return user exists error
            throw new InvalidArgumentException("User Already Exists with username"+createUserRequest.getUsername());
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
        }

        //create User Entity
        User user = new User();
        user.setId(StringUtility.generateID("us"));
        user.setEmail(createUserRequest.getUsername());
        user.setFirstName(createUserRequest.getFirtName());
        user.setLastName(createUserRequest.getLastName());
        user.setPassword(createUserRequest.getPassword());
        user.setActive(1);
        user = userRepository.save(user);
        //get From Security Store
        userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        //Return Result
        return new AuthenticationResponse(jwtService.generateToken(userDetails));
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            return new AuthenticationResponse(jwtService.generateToken(userDetails));
        }catch(BadCredentialsException e) {
            // if not found return user !exists error
            throw new InvalidArgumentException("User doesn't Exists with username"+authenticationRequest.getUsername());

        }
    }
}
