package de.iplytics.codingchallenge_backend_webapp.controller;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.dto.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.PatentService;
import de.iplytics.codingchallenge_backend_webapp.interfaces.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/standards")
public class StandardController {

    @Autowired
    StandardService standardService;

    @GetMapping(path = "/{standardId}", produces = "application/json")
    @ResponseBody
    public StandardResponse getPatent(@PathVariable("standardId") String id){
        return standardService.getSingleStandard(id);
    }
    @DeleteMapping(path = "/{publicationNumber}", produces = "application/json")
    @ResponseBody
    public ResponseMessage delete(@PathVariable("publicationNumber") String id){
        return standardService.delete(id);
    }


    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<StandardResponse> findAll(){
        return standardService.findAll();
    }


    @PostMapping(produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StandardResponse add(@RequestBody StandardRequest standardRequest){
        return standardService.save(standardRequest);
    }

    @PutMapping(produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StandardResponse update(@RequestBody StandardRequest standardRequest){
        return standardService.update(standardRequest);
    }



}
