package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.allteran.sellpo.domain.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductTypeRepository extends MongoRepository<ProductType, String> {
    List<ProductType> findAll();
    ProductType findFirstById(String id);
}
