package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.allteran.sellpo.domain.PointOfSales;

import java.util.List;

public interface POSRepository extends MongoRepository<PointOfSales, Integer> {
    List<PointOfSales> findAll();
    PointOfSales findFirstById(int id);

}
