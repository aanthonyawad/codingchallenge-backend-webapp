package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.PatentService;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.repository.PatentRepository;
import de.iplytics.codingchallenge_backend_webapp.util.DateTimeFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;

@Service
public class PatentServiceImpl implements PatentService {

    private PatentRepository patentRepository;

    @Autowired
    public PatentServiceImpl(PatentRepository patentRepository){
        this.patentRepository = patentRepository;
    }

    @Override
    public PatentResponse getSinglePatent(String publicationNumber){
        return patentRepository.findById(publicationNumber)
                .map(PatentResponse::new)
                .orElseThrow(
                        () -> new ItemNotFoundException("Cannot find patent ID " + publicationNumber)
                );
    }

    @Override
    public List<PatentResponse> findAll() {
        return patentRepository.findAll()
                .stream()
                .map(PatentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public PatentResponse save(PatentRequest patentRequest) {
        Patent patent = new Patent(patentRequest);
        patent = patentRepository.save(patent);
        return new PatentResponse(patent);
    }


    @Override
    public PatentResponse update(PatentRequest patentRequest) {
        this.getSinglePatent(patentRequest.getPublicationNumber());
        return this.save(patentRequest);
    }

    @Override
    public boolean delete(PatentRequest patentRequest) {
        return false;
    }
}
