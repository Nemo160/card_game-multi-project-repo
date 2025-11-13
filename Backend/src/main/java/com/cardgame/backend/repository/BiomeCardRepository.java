package com.cardgame.backend.repository;



import com.cardgame.common.model.BiomeCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BiomeCardRepository extends MongoRepository<BiomeCard,String>{

    List<BiomeCard> findByBiomeId(String id);

}
