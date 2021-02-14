package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.allteran.sellpo.domain.RepairRequest;

import java.util.List;

public interface RepairRequestRepository extends MongoRepository<RepairRequest, String> {
    List<RepairRequest> findAll ();
    //TODO: find requests by status (that is still active)
}
