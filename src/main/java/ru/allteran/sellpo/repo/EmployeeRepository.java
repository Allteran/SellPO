package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.allteran.sellpo.domain.Dealer;
import ru.allteran.sellpo.domain.Employee;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, Double> {
    Employee findByPhone(double phone);

    List<Employee> findByDealer(Dealer dealer);
}
