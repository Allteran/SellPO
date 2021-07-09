package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.allteran.sellpo.models.RepairDeviceStatus;
import ru.allteran.sellpo.models.RepairRequest;

import java.util.List;

@Repository
public interface RepairRequestRepository extends MongoRepository<RepairRequest, String> {
    List<RepairRequest> findAll();

    List<RepairRequest> findAllByStatus(RepairDeviceStatus status);

    RepairRequest findFirstById(String id);
}
