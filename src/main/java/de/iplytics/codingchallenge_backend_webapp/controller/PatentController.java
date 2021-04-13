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


    /**
     * catches error messages thrown from the API calls and return error clear error messages
     */
    @RestControllerAdvice
    public class PatentControllerAdvice{
        @ExceptionHandler(ItemNotFoundException.class)
        @ResponseStatus(value= HttpStatus.BAD_REQUEST)
        public ResponseMessage itemNotFoundException(ItemNotFoundException ex, WebRequest request) {
            return new ResponseMessage(400, ex.toString());
        }

        @ExceptionHandler(InvalidArgumentException.class)
        @ResponseStatus(value= HttpStatus.BAD_REQUEST)
        public ResponseMessage invalidArgumentException(InvalidArgumentException ex, WebRequest request) {
            return new ResponseMessage(400, ex.toString());
        }
    }

}
