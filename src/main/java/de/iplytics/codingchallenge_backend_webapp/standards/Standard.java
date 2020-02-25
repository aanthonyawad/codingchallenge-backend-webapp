package de.iplytics.codingchallenge_backend_webapp.standards;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "standard")
public class Standard {

    @Id
    private String standardId;

    private String name;
    private String description;
}
