package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.domain.Sale;

import java.util.List;
@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
    public List<Sale> findAll();
    public List<Sale> findByProductType(ProductType type);
    public List<Sale> findByDate(String date);
}
