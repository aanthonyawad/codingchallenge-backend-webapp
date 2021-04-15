package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.dto.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.DeclarationService;
import de.iplytics.codingchallenge_backend_webapp.model.Declaration;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.model.Standard;
import de.iplytics.codingchallenge_backend_webapp.repository.DeclarationRepository;
import de.iplytics.codingchallenge_backend_webapp.repository.PatentRepository;
import de.iplytics.codingchallenge_backend_webapp.repository.StandardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeclarationServiceImpl implements DeclarationService {
    private DeclarationRepository declarationRepository;
    private PatentRepository patentRepository;
    private StandardRepository standardRepository;
    private ModelMapperService modelMapper;

    @Autowired
    public DeclarationServiceImpl(DeclarationRepository declarationRepository
                                  ,PatentRepository patentRepository
                                  ,StandardRepository standardRepository
                                  ,ModelMapperService modelMapper){
        this.declarationRepository = declarationRepository;
        this.patentRepository = patentRepository;
        this.standardRepository = standardRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public DeclarationResponse getSingleDeclaration(String declarationId) {
        return  declarationRepository.findById(declarationId)
                .map(modelMapper::convertDeclarationToDto)
                .orElseThrow(
                        () -> new ItemNotFoundException("Cannot find declaration with ID " + declarationId)
                );
    }

    @Override
    public List<DeclarationResponse> findAll() {
        return declarationRepository.findAll()
                .stream()
                .map(modelMapper::convertDeclarationToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeclarationResponse save(DeclarationRequest declarationRequest) {
        Optional<Patent> opatent = patentRepository.findById(declarationRequest.getPublicationNumber());
        Patent patent = opatent.orElseThrow(
                () -> new ItemNotFoundException("Cannot find Patent with ID " + declarationRequest.getPublicationNumber())
        );

        Optional<Standard> ostandard = standardRepository.findById(declarationRequest.getStandardId());
        Standard standard = ostandard.orElseThrow(
                () -> new ItemNotFoundException("Cannot find Declaration with ID " + declarationRequest.getStandardId())
        );
        Declaration declaration = Declaration.builder()
                .declarationId(declarationRequest.getDeclarationId())
                .patent(patent)
                .standard(standard)
                .build();
        declaration = declarationRepository.save(declaration);

        return modelMapper.convertDeclarationToDto(declaration);

    }

    @Override
    public DeclarationResponse update(DeclarationRequest declarationRequest) {
        this.getSingleDeclaration(declarationRequest.getDeclarationId());
        return this.save(declarationRequest);
    }

    @Override
    public ResponseMessage delete(String declarationId) {
        this.getSingleDeclaration(declarationId);
        this.declarationRepository.deleteById(declarationId);
        return new ResponseMessage(200,"Declaration Deleted with ID"+ declarationId);
    }


}
