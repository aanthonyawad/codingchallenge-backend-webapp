package de.iplytics.codingchallenge_backend_webapp.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

/**
 * Created by aanthony Holds the JWT token
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse extends BaseResponse {

    @JsonAlias(value = "jwt")
    private String jwt;
}
