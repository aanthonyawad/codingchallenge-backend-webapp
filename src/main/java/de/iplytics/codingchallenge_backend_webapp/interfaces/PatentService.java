package de.iplytics.codingchallenge_backend_webapp.interfaces;

import de.iplytics.codingchallenge_backend_webapp.model.Patent;

import java.util.List;

public interface PatentService {
    Patent getSinglePatent(String publicationNumber);
    List<Patent> findAll();
    Patent add(Patent patent);
    Patent update(Patent patent);
    boolean delete(Patent patent);
}
