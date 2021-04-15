package de.iplytics.codingchallenge_backend_webapp.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class PatentRequest  implements Serializable {
    @JsonProperty(value = "publicationNumber")
    private String publicationNumber;
    @JsonProperty(value = "publicationDate")
    private String publicationDate;
    @JsonProperty(value = "title")
    private String title;
}
