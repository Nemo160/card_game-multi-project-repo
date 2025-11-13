package com.cardgame.backend.controller;

import com.cardgame.backend.repository.CreatureCardRepository;
import com.cardgame.common.model.CreatureCard;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creature-cards")
public class CreatureCardController {
    private final CreatureCardRepository repo;

    public CreatureCardController(CreatureCardRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public CreatureCard create(@RequestBody CreatureCard card) {
        return repo.save(card);
    }

    @GetMapping
    public List<CreatureCard> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public List<CreatureCard> getById(@PathVariable String id) {
        return repo.findByCreatureId(id);
    }
}
