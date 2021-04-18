package de.iplytics.codingchallenge_backend_webapp.repository;

import de.iplytics.codingchallenge_backend_webapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends SolrCrudRepository<User, String> {
}
