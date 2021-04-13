package de.iplytics.codingchallenge_backend_webapp.interfaces;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;

import java.util.List;

public interface PatentService {
    /**
     *
     * @param publicationNumber field of the Patent DB table
     * @return PatentResponse POJO result which the mapping of the Patent DB table
     * @throws ItemNotFoundException in case of item not found
     */
    PatentResponse getSinglePatent(String publicationNumber);

    /**
     *
     * @return List PatentResponse POJO result which the mapping of the Patent DB table
     */
    List<PatentResponse> findAll();

    /**
     *
     * @param patentRequest POJO of the Patent DB table request
     *  save Patent object in DB and
     * @return PatentResponse POJO result which the mapping of the Patent DB table
     * @throws InvalidArgumentException for invalid argument sent
     */
    PatentResponse save(PatentRequest patentRequest);
    /**
     *
     * @param patentRequest POJO of the Patent DB table request
     *  updates Patent object in DB and
     * @return PatentResponse POJO result which the mapping of the Patent DB table
     * @throws InvalidArgumentException for invalid argument sent
     */
    PatentResponse update(PatentRequest patentRequest);

    /**
     *
     * @param publicationNumber field of the Patent DB table
     * @throws ItemNotFoundException in case of item not found
     */
    ResponseMessage delete(String publicationNumber);
}
