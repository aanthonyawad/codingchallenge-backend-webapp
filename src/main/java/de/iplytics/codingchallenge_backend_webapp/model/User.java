package de.iplytics.codingchallenge_backend_webapp.model;

import de.iplytics.codingchallenge_backend_webapp.util.StringUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.UUID;

@Data
@Builder
@SolrDocument(solrCoreName = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Indexed(name = "id")
    private String id;

    @Indexed(name = "email")
    private String email;

    @Indexed(name = "firstName")
    private String firstName;

    @Indexed(name = "lastName")
    private String lastName;

    @Indexed(name = "password")
    private String password;

    @Indexed(name = "active")
    private int active;

    public User(User user) {
        this.id = user.id;
        this.email = user.email;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.password = user.password;
        this.active = user.active;
    }
}
