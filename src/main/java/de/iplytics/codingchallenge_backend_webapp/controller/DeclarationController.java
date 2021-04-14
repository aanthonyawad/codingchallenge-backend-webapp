package de.iplytics.codingchallenge_backend_webapp.controller;

import de.iplytics.codingchallenge_backend_webapp.dto.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/declarations")
public class DeclarationController {
    @Autowired
    DeclarationService declarationService;

    @GetMapping(path = "/{declarationId}", produces = "application/json")
    @ResponseBody
    public DeclarationResponse getDeclaration(@PathVariable("declarationId") String id){
        return declarationService.getSingleDeclaration(id);
    }
    @DeleteMapping(path = "/{publicationNumber}", produces = "application/json")
    @ResponseBody
    public ResponseMessage delete(@PathVariable("publicationNumber") String id){
        return declarationService.delete(id);
    }


    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<DeclarationResponse> findAll(){
        return declarationService.findAll();
    }


    @PostMapping(produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DeclarationResponse save(@RequestBody(required = true) DeclarationRequest declarationRequest){
        return declarationService.save(declarationRequest);
    }

    @PutMapping(produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DeclarationResponse update(@RequestBody(required = true) DeclarationRequest declarationRequest){
        return declarationService.update(declarationRequest);
    }

}
