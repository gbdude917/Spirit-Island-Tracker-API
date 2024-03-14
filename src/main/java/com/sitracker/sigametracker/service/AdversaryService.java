package com.sitracker.sigametracker.service;

import java.util.*;

import com.sitracker.sigametracker.entity.Adversary;
import org.springframework.http.ResponseEntity;

public interface AdversaryService {

    ResponseEntity<List<Adversary>> getAllAdversaries();

    ResponseEntity<Adversary> getAdversaryById(Long id);

    ResponseEntity<Adversary> getAdversaryByPathname(String pathname);
}
