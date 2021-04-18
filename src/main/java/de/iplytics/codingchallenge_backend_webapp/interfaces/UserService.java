package de.iplytics.codingchallenge_backend_webapp.interfaces;

import de.iplytics.codingchallenge_backend_webapp.dto.request.AuthenticationRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.CreateUserRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.AuthenticationResponse;

public interface UserService {
    /**
     *
     * @param createUserRequest
     * creates a users entry in the user table
     * @return AuthenticationResponse which contains the token in case of success
     * @return ErrorResponse in case of error
     */
    AuthenticationResponse signUp(CreateUserRequest createUserRequest);
    /**
     *
     * @param createUserRequest
     * gets a user entry in the user table
     * @return AuthenticationResponse which contains the token in case of success
     * @return ErrorResponse in case of error
     */
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}