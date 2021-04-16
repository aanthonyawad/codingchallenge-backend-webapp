package de.iplytics.codingchallenge_backend_webapp.model;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.util.DateTimeFormatUtils;
import de.iplytics.codingchallenge_backend_webapp.util.StringUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@SolrDocument(solrCoreName = "patent")
@AllArgsConstructor
@NoArgsConstructor
public class Patent implements Serializable {

    @Id
    @Indexed(name = "publicationNumber", type = "string")
    private String publicationNumber;
    @Indexed(name = "publicationDate", type = "string")
    private String publicationDate;
    @Indexed(name = "title", type = "string")
    private String title;
}
