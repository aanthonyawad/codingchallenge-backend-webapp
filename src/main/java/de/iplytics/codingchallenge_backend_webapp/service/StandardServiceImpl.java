package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.dto.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.StandardService;
import de.iplytics.codingchallenge_backend_webapp.model.Standard;
import de.iplytics.codingchallenge_backend_webapp.repository.StandardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StandardServiceImpl implements StandardService {


    private StandardRepository standardRepository;

    private ModelMapper modelMapper;

    @Autowired
    public StandardServiceImpl(StandardRepository standardRepository,ModelMapper modelMapper){
        this.standardRepository = standardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StandardResponse getSingleStandard(String standardId) {
        return standardRepository.findById(standardId)
                .map(this::convertToDto)
                .orElseThrow(
                        () -> new ItemNotFoundException("Cannot find standard ID " + standardId)
                );
    }

    @Override
    public List<StandardResponse> findAll() {
        return standardRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StandardResponse save(StandardRequest standardRequest) {
        Standard standard = this.convertToEntity(standardRequest);
        standard = standardRepository.save(standard);
        return this.convertToDto(standard);
    }

    @Override
    public StandardResponse update(StandardRequest standardRequest) {
        this.getSingleStandard(standardRequest.getStandardId());
        return this.save(standardRequest);
    }

    @Override
    public ResponseMessage delete(String standardId) {
        this.getSingleStandard(standardId);
        this.standardRepository.deleteById(standardId);
        return new ResponseMessage(200,"Standard Deleted with ID"+ standardId);
    }


    public Standard convertToEntity(StandardRequest standardRequest) {
        Standard standard = this.modelMapper.map(standardRequest, Standard.class);
        return standard;
    }

    public StandardResponse convertToDto(Standard standard) {
        StandardResponse standardResponse = this.modelMapper.map(standard, StandardResponse.class);
        return standardResponse;
    }
}
