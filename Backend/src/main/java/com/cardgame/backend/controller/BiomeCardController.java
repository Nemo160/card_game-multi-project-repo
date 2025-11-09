package com.cardgame.backend.controller;

import com.cardgame.backend.repository.BiomeCardRepository;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/biomes")
public class BiomeCardController {
    private final BiomeCardRepository repo;

    public BiomeCardController(BiomeCardRepository repo) {
        this.repo = repo;
    }
}
