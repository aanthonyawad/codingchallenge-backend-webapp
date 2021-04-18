package de.iplytics.codingchallenge_backend_webapp.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AuthenticationRequest {
    @JsonAlias(value = "username")
    private String username;

    @JsonAlias(value = "password")
    private String password;
}