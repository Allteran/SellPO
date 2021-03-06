package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.allteran.sellpo.domain.RepairDeviceStatus;
import ru.allteran.sellpo.domain.RepairRequest;

import java.util.List;

@Repository
public interface RepairRequestRepository extends MongoRepository<RepairRequest, String> {
    List<RepairRequest> findAll();

    List<RepairRequest> findAllByStatus(RepairDeviceStatus status);

    RepairRequest findFirstById(String id);
}
