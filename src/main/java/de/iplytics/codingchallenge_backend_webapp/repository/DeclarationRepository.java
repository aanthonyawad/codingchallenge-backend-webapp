package de.iplytics.codingchallenge_backend_webapp.repository;

import de.iplytics.codingchallenge_backend_webapp.model.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclarationRepository extends JpaRepository<Declaration, String> {
}
