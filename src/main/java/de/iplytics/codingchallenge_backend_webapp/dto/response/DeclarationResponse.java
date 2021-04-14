package de.iplytics.codingchallenge_backend_webapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeclarationResponse implements Serializable {

    @JsonProperty(value = "patentResponse")
    private PatentResponse patentResponse;

    @JsonProperty(value = "standardResponse")
    private StandardResponse standardResponse;
}
