package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.RepairRequest;
import ru.allteran.sellpo.repo.RepairRequestRepository;

import java.util.List;

@Service
public class RepairService {
    @Autowired
    private RepairRequestRepository repairRepo;

    public void createRepairRequest(RepairRequest request) {
        repairRepo.save(request);
    }

    public List<RepairRequest> findAll() {
        return repairRepo.findAll();
    }
}
