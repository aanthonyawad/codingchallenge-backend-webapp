package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.PatentService;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.repository.PatentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatentServiceImpl implements PatentService {

    private PatentRepository patentRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PatentServiceImpl(PatentRepository patentRepository,ModelMapper modelMapper){
        this.patentRepository = patentRepository;
        this.modelMapper = modelMapper;
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
        Patent post = this.modelMapper.map(patentRequest, Patent.class);
        post.setPublicationDateDto(patentRequest.getPublicationDate());
        return post;
    }

    public PatentResponse convertToDto(Patent patent) {
        PatentResponse patentResponse = this.modelMapper.map(patent, PatentResponse.class);
        patentResponse.setPublicationDateDto(patent.getPublicationDate());
        return patentResponse;
    }
}
