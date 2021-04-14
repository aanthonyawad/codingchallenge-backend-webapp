package de.iplytics.codingchallenge_backend_webapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.util.DateTimeFormatUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatentResponse implements Serializable {
    @JsonProperty(value = "publicationNumber")
    private String publicationNumber;
    @JsonProperty(value = "publicationDate")
    private String publicationDate;
    @JsonProperty(value = "title")
    private String title;

    public void setPublicationDateDto(LocalDate localDate) {
        this.publicationDate = DateTimeFormatUtils.DateToString(localDate);
    }

}
