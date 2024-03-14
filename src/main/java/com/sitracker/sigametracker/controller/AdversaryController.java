package com.sitracker.sigametracker.controller;

import java.util.*;

import com.sitracker.sigametracker.entity.Adversary;
import com.sitracker.sigametracker.service.AdversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adversaries")
public class AdversaryController {

    private final AdversaryService adversaryService;

    @Autowired
    public AdversaryController(AdversaryService adversaryService) {
        this.adversaryService = adversaryService;
    }

    /**
     * Retrieve all the Adversaries entities in the database
     *
     * @return A List of Adversary entities from the database
     */
    @GetMapping
    public ResponseEntity<List<Adversary>> getAllAdversaries() {
        return adversaryService.getAllAdversaries();
    }

    /**
     * Retrieve a specific adversary by id
     *
     * @param id The unique identifier for the adversary to be found
     * @return The Adversary entity that matches the id parameter. Otherwise, 404 Not Found is thrown.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Adversary> getAdversaryById(@PathVariable Long id) {
        return adversaryService.getAdversaryById(id);
    }

    /**
     * Retrieve a specific adversary by pathname
     *
     * @param pathname The pathname corresponding ot the name of the adversary to be found
     * @return The Adversary entity that matches the pathname parameter. Otherwise, 404 Not Found is thrown
     */
    @GetMapping("/pathname/{pathname}")
    public ResponseEntity<Adversary> getAdversaryByPathname(@PathVariable String pathname) {
        return adversaryService.getAdversaryByPathname(pathname);
    }

}
