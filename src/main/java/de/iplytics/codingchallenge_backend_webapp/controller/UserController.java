package de.iplytics.codingchallenge_backend_webapp.controller;

import de.iplytics.codingchallenge_backend_webapp.dto.request.AuthenticationRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.CreateUserRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.AuthenticationResponse;
import de.iplytics.codingchallenge_backend_webapp.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value="/signup")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(this.userService.signUp(createUserRequest));
    }

    @PostMapping(value="/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(this.userService.login(authenticationRequest));
    }
}
