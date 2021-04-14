package de.iplytics.codingchallenge_backend_webapp.interfaces;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.dto.response.StandardResponse;

import java.util.List;

public interface StandardService {
    /**
     *
     * @param standardId field of the Standard DB table
     * @return StandardResponse POJO result which the mapping of the Standard DB table
     * @throws ItemNotFoundException in case of item not found
     */
    StandardResponse getSingleStandard(String standardId);

    /**
     *
     * @return List StandardResponse POJO result which the mapping of the Standard DB table
     */
    List<StandardResponse> findAll();

    /**
     *
     * @param standardRequest POJO of the Standard DB table request
     *  save Patent object in DB and
     * @return StandardResponse POJO result which the mapping of the Standard DB table
     * @throws InvalidArgumentException for invalid argument sent
     */
    StandardResponse save(StandardRequest standardRequest);
    /**
     *
     * @param standardRequest POJO of the Standard DB table request
     *  updates Patent object in DB and
     * @return StandardResponse POJO result which the mapping of the Standard DB table
     * @throws InvalidArgumentException for invalid argument sent
     */
    StandardResponse update(StandardRequest standardRequest);

    /**
     *
     * @param standardId field of the Standard DB table
     * @throws ItemNotFoundException in case of item not found
     */
    ResponseMessage delete(String standardId);
}
