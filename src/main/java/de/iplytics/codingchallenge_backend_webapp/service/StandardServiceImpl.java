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

    private ModelMapperService modelMapper;

    @Autowired
    public StandardServiceImpl(StandardRepository standardRepository,ModelMapperService modelMapper){
        this.standardRepository = standardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StandardResponse getSingleStandard(String standardId) {
        return standardRepository.findById(standardId)
                .map(modelMapper::convertStandardToDto)
                .orElseThrow(
                        () -> new ItemNotFoundException("Cannot find standard ID " + standardId)
                );
    }

    @Override
    public List<StandardResponse> findAll() {
        return standardRepository.findAll()
                .stream()
                .map(modelMapper::convertStandardToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StandardResponse save(StandardRequest standardRequest) {
        Standard standard = modelMapper.convertStandardToEntity(standardRequest);
        standard = standardRepository.save(standard);
        return modelMapper.convertStandardToDto(standard);
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

}
