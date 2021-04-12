package de.iplytics.codingchallenge_backend_webapp.controller;

import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.service.PatentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patents")
public class PatentController {

    @Autowired
    PatentServiceImpl patentService;

    @GetMapping("/{publicationNumber}")
    @ResponseBody
    public Patent getPatent(@PathVariable("publicationNumber") String id){
        return patentService.getSinglePatent(id);
    }
}
