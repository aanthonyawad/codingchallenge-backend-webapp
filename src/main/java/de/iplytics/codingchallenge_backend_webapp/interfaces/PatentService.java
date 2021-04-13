package de.iplytics.codingchallenge_backend_webapp.interfaces;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;

import java.util.List;

public interface PatentService {
    PatentResponse getSinglePatent(String publicationNumber);
    List<PatentResponse> findAll();
    PatentResponse save(PatentRequest patentRequest);
    PatentResponse update(PatentRequest patentRequest);
    boolean delete(PatentRequest patentRequest);
}
