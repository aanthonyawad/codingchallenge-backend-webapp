package de.iplytics.codingchallenge_backend_webapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse  implements Serializable {
    private int code;
    private String message;
}
