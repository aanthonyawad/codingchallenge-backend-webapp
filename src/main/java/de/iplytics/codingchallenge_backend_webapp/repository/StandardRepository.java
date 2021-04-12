package de.iplytics.codingchallenge_backend_webapp.repository;

import de.iplytics.codingchallenge_backend_webapp.model.Standard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRepository extends CrudRepository<Standard, String> {
}
