package com.sitracker.sigametracker.service;

import com.sitracker.sigametracker.entity.Spirit;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpiritService {
    ResponseEntity<List<Spirit>> getAllSpirits();

    ResponseEntity<Spirit> getSpiritById(Long id);

    ResponseEntity<Spirit> getSpiritByPathname(String pathname);
}
