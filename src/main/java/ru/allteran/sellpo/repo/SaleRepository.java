package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.domain.Sale;

import java.util.List;
@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
    List<Sale> findAll();
    List<Sale> findByProductType(ProductType type);
    List<Sale> findByDate(String date);
}
