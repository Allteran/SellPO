package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.allteran.sellpo.domain.Dealer;

import java.util.List;

@Repository
public interface DealerRepository extends MongoRepository<Dealer, String> {
    List<Dealer> findAll();
    Dealer findFirstById(String id);
}
