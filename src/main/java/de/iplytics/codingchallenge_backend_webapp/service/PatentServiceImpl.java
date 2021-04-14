package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.PatentService;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.repository.PatentRepository;
import de.iplytics.codingchallenge_backend_webapp.util.DateTimeFormatUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;

@Service
public class PatentServiceImpl implements PatentService {

    private PatentRepository patentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PatentServiceImpl(PatentRepository patentRepository){
        this.patentRepository = patentRepository;
    }

    @Override
    public PatentResponse getSinglePatent(String publicationNumber){
        return patentRepository.findById(publicationNumber)
                .map(this::convertToDto)
                .orElseThrow(
                        () -> new ItemNotFoundException("Cannot find patent ID " + publicationNumber)
                );
    }

    @Override
    public List<PatentResponse> findAll() {
        return patentRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatentResponse save(PatentRequest patentRequest) {
        Patent patent = this.convertToEntity(patentRequest);
        patent = patentRepository.save(patent);
        return this.convertToDto(patent);
    }


    @Override
    public PatentResponse update(PatentRequest patentRequest) {
        this.getSinglePatent(patentRequest.getPublicationNumber());
        return this.save(patentRequest);
    }

    @Override
    public ResponseMessage delete(String publicationNumber) {
        this.getSinglePatent(publicationNumber);
        this.patentRepository.deleteById(publicationNumber);
        return new ResponseMessage(200,"Patent Deleted with ID"+ publicationNumber);
    }

    public Patent convertToEntity(PatentRequest patentRequest) {
        Patent post = modelMapper.map(patentRequest, Patent.class);
        return post;
    }

    public PatentResponse convertToDto(Patent patent) {
        PatentResponse patentResponse = modelMapper.map(patent, PatentResponse.class);
        return patentResponse;
    }
}
