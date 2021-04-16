package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.DeclarationService;
import de.iplytics.codingchallenge_backend_webapp.model.Declaration;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.model.Standard;
import de.iplytics.codingchallenge_backend_webapp.repository.DeclarationRepository;
import de.iplytics.codingchallenge_backend_webapp.repository.PatentRepository;
import de.iplytics.codingchallenge_backend_webapp.repository.StandardRepository;
import de.iplytics.codingchallenge_backend_webapp.util.StringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
        Declaration declaration = declarationRepository.findById(declarationId).orElseThrow(
                                () -> new ItemNotFoundException("Cannot find declaration with ID " + declarationId)
                        );

        Patent patent = patentRepository.findById(declaration.getPublicationNumber()).orElseThrow(
                () -> new ItemNotFoundException("Cannot find Patent with ID " + declaration.getPublicationNumber())
                    );
        Standard standard= standardRepository.findById(declaration.getStandardId()).orElseThrow(
                () -> new ItemNotFoundException("Cannot find Standard with ID " + declaration.getStandardId())
                );
        return modelMapper.convertDeclarationToDto(declaration,patent,standard);
    }

    @Override
    public List<DeclarationResponse> findAll() {
        Iterable<Declaration> declarations = declarationRepository.findAll();
        Iterator<Declaration> iterator = declarations.iterator();
        List<DeclarationResponse> result = new ArrayList<>();
        while (iterator.hasNext()){
            result.add(this.getSingleDeclaration(iterator.next().getDeclarationId()));
        }
        return result;
    }

    @Override
    public DeclarationResponse save(DeclarationRequest declarationRequest) {
        this.checkDeclarationRequest(declarationRequest);

        Declaration declaration = modelMapper.convertDeclarationToEntity(declarationRequest);

        Patent patent = patentRepository.findById(declaration.getPublicationNumber()).orElseThrow(
                () -> new ItemNotFoundException("Cannot find Patent with ID " + declaration.getPublicationNumber())
        );
        Standard standard= standardRepository.findById(declaration.getStandardId()).orElseThrow(
                () -> new ItemNotFoundException("Cannot find Standard with ID " + declaration.getStandardId())
        );
        this.declarationRepository.save(declaration);
        return modelMapper.convertDeclarationToDto(declaration,patent,standard);

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

    private void checkDeclarationRequest(DeclarationRequest declarationRequest) {

        if(StringUtility.isEmptyOrNull(declarationRequest.getPublicationNumber()))
            throw new InvalidArgumentException("Declaration Publication Number not Found");
        if(StringUtility.isEmptyOrNull(declarationRequest.getDeclarationId()))
            throw new InvalidArgumentException("Declaration Declaration Id not Found");
        if(StringUtility.isEmptyOrNull(declarationRequest.getStandardId()))
            throw new InvalidArgumentException("Declaration Standard Id not Found");
    }
}
