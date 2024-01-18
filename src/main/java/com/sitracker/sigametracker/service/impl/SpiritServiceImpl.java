package com.sitracker.sigametracker.service.impl;

import java.util.*;

import com.sitracker.sigametracker.entity.Spirit;
import com.sitracker.sigametracker.repository.SpiritRepository;
import com.sitracker.sigametracker.service.SpiritService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpiritServiceImpl implements SpiritService {

    private final SpiritRepository spiritRepository;

    @Autowired
    public SpiritServiceImpl(SpiritRepository spiritRepository) {
        this.spiritRepository = spiritRepository;
    }

    @Override
    public ResponseEntity<List<Spirit>> getAllSpirits() {
        return new ResponseEntity<>(spiritRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Spirit> getSpiritById(Long id) {
        Optional<Spirit> spiritOptional = spiritRepository.findById(id);

        return spiritOptional.map(spirit -> new ResponseEntity<>(spirit, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
