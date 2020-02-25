package de.iplytics.codingchallenge_backend_webapp.patents;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
@Entity(name = "patent")
public class Patent {

    @Id
    private String publicationNumber;
    private LocalDate publicationDate;
    private String title;
}
