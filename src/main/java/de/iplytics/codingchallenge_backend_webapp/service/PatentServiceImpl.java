package de.iplytics.codingchallenge_backend_webapp.service;

import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundExxception;
import de.iplytics.codingchallenge_backend_webapp.interfaces.PatentService;
import de.iplytics.codingchallenge_backend_webapp.model.Patent;
import de.iplytics.codingchallenge_backend_webapp.repository.PatentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatentServiceImpl implements PatentService {

    private PatentRepository patentRepository;

    @Autowired
    public PatentServiceImpl(PatentRepository patentRepository){
        this.patentRepository = patentRepository;
    }

    @Override
    public Patent getSinglePatent(String publicationNumber){
        return patentRepository.findById(publicationNumber)
                .orElseThrow(
                        () -> new ItemNotFoundExxception("Cannot find patent ID " + publicationNumber)
                );
    }

    @Override
    public List<Patent> findAll() {
        return patentRepository.findAll()
                .stream()
//                .map()
                .collect(Collectors.toList());
    }

    @Override
    public Patent add(Patent patent) {
        return null;
    }

    @Override
    public Patent update(Patent patent) {
        return null;
    }

    @Override
    public boolean delete(Patent patent) {
        return false;
    }
}
