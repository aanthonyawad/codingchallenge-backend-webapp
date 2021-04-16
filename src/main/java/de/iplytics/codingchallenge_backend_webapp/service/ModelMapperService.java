package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.dto.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.model.Declaration;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.model.Standard;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {

    @Autowired
    ModelMapper modelMapper;

    public Standard convertStandardToEntity(StandardRequest standardRequest) {
        Standard standard = this.modelMapper.map(standardRequest, Standard.class);
        return standard;
    }

    public StandardResponse convertStandardToDto(Standard standard) {
        StandardResponse standardResponse = this.modelMapper.map(standard, StandardResponse.class);
        return standardResponse;
    }

    public Patent convertPatentToEntity(PatentRequest patentRequest) {
        Patent post = this.modelMapper.map(patentRequest, Patent.class);
        return post;
    }

    public PatentResponse convertPatentToDto(Patent patent) {
        PatentResponse patentResponse = this.modelMapper.map(patent, PatentResponse.class);
        return patentResponse;
    }




    public Declaration convertDeclarationToEntity(DeclarationRequest declarationRequest) {
        Declaration declaration = this.modelMapper.map(declarationRequest, Declaration.class);

        return declaration;
    }

    public DeclarationResponse convertDeclarationToDto(Declaration declaration,Patent patent, Standard standard) {
        DeclarationResponse declarationResponse = this.modelMapper.map(declaration, DeclarationResponse.class);
        declarationResponse.setPatentResponse(this.convertPatentToDto(patent));
        declarationResponse.setStandardResponse(this.convertStandardToDto(standard));
        return declarationResponse;
    }

}
