package de.iplytics.codingchallenge_backend_webapp.controller;

import de.iplytics.codingchallenge_backend_webapp.dto.response.ErrorResponse;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundExxception;
import de.iplytics.codingchallenge_backend_webapp.interfaces.PatentService;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.service.PatentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Patent getPatent(@PathVariable("publicationNumber") String id){
        return patentService.getSinglePatent(id);
    }


    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<Patent> findAll(){
        return patentService.findAll();
    }


    /**
     * catches error messages thrown from the API calls and return error clear error messages
     */
    @RestControllerAdvice
    public class PatentControllerAdvice{
        @ExceptionHandler(ItemNotFoundExxception.class)
        @ResponseStatus(value= HttpStatus.NOT_FOUND)
        public ErrorResponse incompleteRequestException(ItemNotFoundExxception ex, WebRequest request) {
            return new ErrorResponse(404, ex.toString());
        }
    }

}
