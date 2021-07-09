package ru.allteran.sellpo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.allteran.sellpo.models.RepairDeviceStatus;

import java.util.List;

@Repository
public interface RepairDeviceStatusRepository extends MongoRepository<RepairDeviceStatus, String> {
    List<RepairDeviceStatus> findAll();

    RepairDeviceStatus findFirstById(String id);
}
