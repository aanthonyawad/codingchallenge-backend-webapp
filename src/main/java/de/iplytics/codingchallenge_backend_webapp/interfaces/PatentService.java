package de.iplytics.codingchallenge_backend_webapp.interfaces;

import de.iplytics.codingchallenge_backend_webapp.model.Patent;

public interface PatentService {
    Patent getSinglePatent(String publicationNumber);
}
