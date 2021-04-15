package de.iplytics.codingchallenge_backend_webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;


@Data
@Builder
@SolrDocument(solrCoreName = "standard")
@AllArgsConstructor
@NoArgsConstructor
public class Standard {

    @Id
    @Indexed(name = "standardId", type = "string")
    private String standardId;
    @Indexed(name = "name", type = "string")
    private String name;
    @Indexed(name = "description", type = "string")
    private String description;

}
