package com.sitracker.sigametracker.controller;

import java.util.*;

import com.sitracker.sigametracker.entity.Spirit;
import com.sitracker.sigametracker.service.SpiritService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/spirits")
public class SpiritController {

    private final SpiritService spiritService;

    @Autowired
    public SpiritController(SpiritService spiritService) {
        this.spiritService = spiritService;
    }

    /**
     * Retrieve all the Spirit entities in the database
     *
     * @return A List of all Spirit entities in the database
     */
    @GetMapping
    public ResponseEntity<List<Spirit>> getAllSpirits() {
        return spiritService.getAllSpirits();
    }

    /**
     * Retrieve a specific spirit by id
     *
     * @param id The unique identifier for a spirit to be found
     * @return The Spirit entity that matches the id parameter. Otherwise, 404 Not Found is thrown.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Spirit> getSpiritById(@PathVariable Long id) {
        return spiritService.getSpiritById(id);
    }

    /**
     * Retrieve a specific spirit by pathname
     *
     * @param pathname The pathname corresponding ot the name of the spirit to be found
     * @return The Spirit entity that matches the pathname parameter. Otherwise, 404 Not Found is thrown
     */
    @GetMapping("/pathname/{pathname}")
    public ResponseEntity<Spirit> getSpiritByPathname(@PathVariable String pathname) { return spiritService.getSpiritByPathname(pathname); }
}
