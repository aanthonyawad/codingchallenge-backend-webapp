package de.iplytics.codingchallenge_backend_webapp.interfaces;


import de.iplytics.codingchallenge_backend_webapp.dto.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;

import java.util.List;

public interface DeclarationService {
    /**
     *
     * @param declarationId field of the Declaration DB table
     * @return DeclarationResponse POJO result which the mapping of the Declaration DB table
     * @throws ItemNotFoundException in case of item not found
     */
    DeclarationResponse getSingleDeclaration(String declarationId);

    /**
     *
     * @return List DeclarationResponse POJO result which the mapping of the Declaration DB table
     */
    List<DeclarationResponse> findAll();

    /**
     *
     * @param declarationRequest POJO of the Declaration DB table request
     *  save Patent object in DB and
     * @return DeclarationResponse POJO result which the mapping of the Declaration DB table
     * @throws InvalidArgumentException for invalid argument sent
     */
    DeclarationResponse save(DeclarationRequest declarationRequest);
    /**
     *
     * @param declarationRequest POJO of the Declaration DB table request
     *  updates Patent object in DB and
     * @return DeclarationResponse POJO result which the mapping of the Declaration DB table
     * @throws InvalidArgumentException for invalid argument sent
     */
    DeclarationResponse update(DeclarationRequest declarationRequest);

    /**
     *
     * @param publicationNumber field of the Patent DB table
     * @throws ItemNotFoundException in case of item not found
     */
    ResponseMessage delete(String declarationId);
}