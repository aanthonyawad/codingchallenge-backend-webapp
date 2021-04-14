package de.iplytics.codingchallenge_backend_webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity(name = "declaration")
@AllArgsConstructor
@NoArgsConstructor
public class Declaration {
    @Id
    private String declarationId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patent_id", referencedColumnName = "publicationNumber")
    private Patent patent ;

    @ManyToOne
    @JoinColumn(name = "standard_id", nullable = false)
    private Standard standard;
}
