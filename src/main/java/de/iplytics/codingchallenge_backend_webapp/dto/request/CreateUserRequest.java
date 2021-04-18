package de.iplytics.codingchallenge_backend_webapp.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateUserRequest {
    @JsonAlias(value = "username")
    private String username;

    @JsonAlias(value = "first_name")
    private String firtName;

    @JsonAlias(value = "last_name")
    private String lastName;

    @JsonAlias(value = "password")
    private String password;
}

