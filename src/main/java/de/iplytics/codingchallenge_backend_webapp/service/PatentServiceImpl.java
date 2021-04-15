package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.dto.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.PatentService;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.model.Standard;
import de.iplytics.codingchallenge_backend_webapp.repository.PatentRepository;
import de.iplytics.codingchallenge_backend_webapp.util.StringUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
//        return patentRepository.findAll()
//                .stream()
//                .map(modelMapper::convertPatentToDto)
//                .collect(Collectors.toList());
        Iterable<Patent> patents = patentRepository.findAll();
        Iterator<Patent> patentIterator = patents.iterator();
        List<PatentResponse> result = new ArrayList<>();
        while (patentIterator.hasNext()){
            result.add( modelMapper.convertPatentToDto(patentIterator.next()));
        }
        return result;
    }

    @Override
    public PatentResponse save(PatentRequest patentRequest) {
        this.checkPatentRequest(patentRequest);
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

    private void checkPatentRequest(PatentRequest patentRequest){
        if(StringUtility.isEmptyOrNull(patentRequest.getPublicationNumber()))
            throw new InvalidArgumentException("Patent Publication Number not Found");
        if(StringUtility.isEmptyOrNull(patentRequest.getTitle()))
            throw new InvalidArgumentException("Patent Title not Found");
        if(StringUtility.isEmptyOrNull(patentRequest.getPublicationDate()))
            throw new InvalidArgumentException("Patent Publication Number not Found");
    }

}
