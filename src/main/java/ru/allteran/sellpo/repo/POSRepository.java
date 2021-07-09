package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.allteran.sellpo.models.PointOfSales;

import java.util.List;

public interface POSRepository extends MongoRepository<PointOfSales, Integer> {
    List<PointOfSales> findAll();

    PointOfSales findFirstById(String id);

}
