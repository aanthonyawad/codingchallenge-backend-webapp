package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.dto.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.interfaces.StandardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardServiceImpl implements StandardService {
    @Override
    public StandardResponse getSingleStandard(String standardId) {
        return null;
    }

    @Override
    public List<StandardResponse> findAll() {
        return null;
    }

    @Override
    public StandardResponse save(StandardRequest standardRequest) {
        return null;
    }

    @Override
    public StandardResponse update(StandardRequest standardRequest) {
        return null;
    }

    @Override
    public ResponseMessage delete(String standardId) {
        return null;
    }
}
