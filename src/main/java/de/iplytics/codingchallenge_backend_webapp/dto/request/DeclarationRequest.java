package de.iplytics.codingchallenge_backend_webapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeclarationRequest {

    @JsonProperty(value = "declarationId")
    private String declarationId;

    @JsonProperty(value = "publicationNumber")
    private String publicationNumber;

    @JsonProperty(value = "standardId")
    private String standardId;
}
