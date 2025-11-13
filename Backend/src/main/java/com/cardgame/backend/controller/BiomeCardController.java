package com.cardgame.backend.controller;

import com.cardgame.backend.repository.BiomeCardRepository;
import com.cardgame.common.model.BiomeCard;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biome-cards")
public class BiomeCardController {
    private final BiomeCardRepository repo;

    public BiomeCardController(BiomeCardRepository repo) {
        this.repo = repo;
    }

    public List<BiomeCard> getAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public List<BiomeCard> getById(@PathVariable String id){

        return repo.findByBiomeId(id);
    }
    @PostMapping("/{id}")
    public BiomeCard create(@PathVariable BiomeCard card){
        return repo.save(card);
    }

    @PutMapping("/{id}")
    public BiomeCard create(@PathVariable String id, @RequestBody BiomeCard card){
        card.setBiomeId(id);
        return repo.save(card);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        repo.deleteById(id);
    }
}
