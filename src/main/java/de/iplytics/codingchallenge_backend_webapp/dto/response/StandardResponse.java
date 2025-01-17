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
public class StandardResponse implements Serializable {
    @JsonProperty(value = "standardId")
    private String standardId;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "description")
    private String description;
}
