package de.iplytics.codingchallenge_backend_webapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity(name = "standard")
public class Standard {

    @Id
    private String standardId;
    private String name;
    private String description;
    @OneToMany(mappedBy = "standard")
    private Set<Declaration> declarations;

}
