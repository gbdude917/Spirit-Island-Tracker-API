package com.sitracker.sigametracker.service.impl;

import java.util.*;

import com.sitracker.sigametracker.entity.Adversary;
import com.sitracker.sigametracker.repository.AdversaryRepository;
import com.sitracker.sigametracker.service.AdversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdversaryServiceImpl implements AdversaryService {

    private final AdversaryRepository adversaryRepository;

    @Autowired
    public AdversaryServiceImpl(AdversaryRepository adversaryRepository) {
        this.adversaryRepository = adversaryRepository;
    }

    @Override
    public ResponseEntity<List<Adversary>> getAllAdversaries() {
        return new ResponseEntity<>(adversaryRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Adversary> getAdversaryById(Long id) {
        Optional<Adversary> adversaryOptional = adversaryRepository.findById(id);

        return adversaryOptional.map(adversary -> new ResponseEntity<>(adversary, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
