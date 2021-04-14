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

    private ModelMapperService modelMapper;

    @Autowired
    public PatentServiceImpl(PatentRepository patentRepository,ModelMapperService modelMapper){
        this.patentRepository = patentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatentResponse getSinglePatent(String publicationNumber){
        return patentRepository.findById(publicationNumber)
                .map(modelMapper::convertPatentToDto)
                .orElseThrow(
                        () -> new ItemNotFoundException("Cannot find patent ID " + publicationNumber)
                );
    }

    @Override
    public List<PatentResponse> findAll() {
        return patentRepository.findAll()
                .stream()
                .map(modelMapper::convertPatentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatentResponse save(PatentRequest patentRequest) {
        Patent patent = modelMapper.convertPatentToEntity(patentRequest);
        patent = patentRepository.save(patent);
        return modelMapper.convertPatentToDto(patent);
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

}
