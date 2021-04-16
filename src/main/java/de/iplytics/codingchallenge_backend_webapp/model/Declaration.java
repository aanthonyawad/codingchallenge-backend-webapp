package de.iplytics.codingchallenge_backend_webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.time.LocalDate;

@Data
@Builder
@SolrDocument(collection = "declaration")
@AllArgsConstructor
@NoArgsConstructor
public class Declaration {

    @Id
    @Indexed(name = "declarationId", type = "string")
    private String declarationId;
    @Indexed(name = "publicationNumber", type = "string")
    private String publicationNumber;
    @Indexed(name = "standardId", type = "string")
    private String standardId;
}
