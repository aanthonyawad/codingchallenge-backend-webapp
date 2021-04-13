package de.iplytics.codingchallenge_backend_webapp.repository;

import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatentRepository extends JpaRepository<Patent, String> {
}
