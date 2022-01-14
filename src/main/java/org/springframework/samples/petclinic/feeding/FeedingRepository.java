package org.springframework.samples.petclinic.feeding;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FeedingRepository extends CrudRepository<Feeding, Integer>{
    List<Feeding> findAll();
    
    @Query(value="SELECT ft FROM FeedingType ft ORDER BY ft.name")
    List<FeedingType> findAllFeedingTypes();
    Optional<Feeding> findById(int id);
    Feeding save(Feeding p);
    
    @Query(value="SELECT ft FROM FeedingType ft WHERE ft.name=?1")
    FeedingType findFeedingTypeByName(String name);
}
