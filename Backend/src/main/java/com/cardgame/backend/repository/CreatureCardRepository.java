package com.cardgame.backend.repository;
import com.cardgame.common.model.CreatureCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CreatureCardRepository extends MongoRepository<CreatureCard, String> {
    List<CreatureCard> findByCreatureId(String id);
}
