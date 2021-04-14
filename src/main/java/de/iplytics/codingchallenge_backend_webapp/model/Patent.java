package de.iplytics.codingchallenge_backend_webapp.model;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.util.DateTimeFormatUtils;
import de.iplytics.codingchallenge_backend_webapp.util.StringUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@Entity(name = "patent")
@AllArgsConstructor
@NoArgsConstructor
public class Patent implements Serializable {

    @Id
    private String publicationNumber;
    private LocalDate publicationDate;
    private String title;
    @OneToOne(mappedBy = "patent")
    private Declaration declaration;

    public void setPublicationDateDto(String date) {
        this.publicationDate = DateTimeFormatUtils.StringToDate(date);
    }
}
