package de.iplytics.codingchallenge_backend_webapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.util.DateTimeFormatUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatentResponse extends BaseResponse implements Serializable {
    @JsonProperty(value = "publicationNumber")
    private String publicationNumber;
    @JsonProperty(value = "publicationDate")
    private String publicationDate;
    @JsonProperty(value = "title")
    private String title;

    public PatentResponse(Patent patent){
        this.publicationNumber = patent.getPublicationNumber();
        this.publicationDate  = DateTimeFormatUtils.DateToString(patent.getPublicationDate());
        this.title = patent.getTitle();
    }

}
