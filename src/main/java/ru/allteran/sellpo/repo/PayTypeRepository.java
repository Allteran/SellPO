package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.allteran.sellpo.domain.PayType;

import java.util.List;

public interface PayTypeRepository extends MongoRepository<PayType, String> {
    List<PayType> findAll();

    PayType findFirstById(String id);
}
