package de.iplytics.codingchallenge_backend_webapp.controller;

import de.iplytics.codingchallenge_backend_webapp.dto.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.dto.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.interfaces.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/patents")
public class PatentController {

    @Autowired
    PatentService patentService;

    @GetMapping(path = "/{publicationNumber}", produces = "application/json")
    @ResponseBody
    public PatentResponse getPatent(@PathVariable("publicationNumber") String id){
        return patentService.getSinglePatent(id);
    }
    @DeleteMapping(path = "/{publicationNumber}", produces = "application/json")
    @ResponseBody
    public ResponseMessage delete(@PathVariable("publicationNumber") String id){
        return patentService.delete(id);
    }


    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<PatentResponse> findAll(){
        return patentService.findAll();
    }


    @PostMapping(produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatentResponse add(@RequestBody PatentRequest patentRequest){
        return patentService.save(patentRequest);
    }

    @PutMapping(produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatentResponse update(@RequestBody PatentRequest patent){
        return patentService.update(patent);
    }




}
