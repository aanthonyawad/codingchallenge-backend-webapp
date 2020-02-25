package de.iplytics.codingchallenge_backend_webapp.patents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatentService {

    @Autowired
    private PatentRepository patentRepository;

    public Patent getSinglePatent(String publicationNumber){
        return patentRepository.findById(publicationNumber)
                .orElseThrow(
                        () -> new IllegalArgumentException("Cannot find patent ID " + publicationNumber)
                );
    }
}
