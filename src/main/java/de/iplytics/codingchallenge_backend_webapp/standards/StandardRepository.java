package de.iplytics.codingchallenge_backend_webapp.standards;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRepository extends CrudRepository<Standard, String> {
}
