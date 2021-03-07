package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.*;
import ru.allteran.sellpo.repo.POSRepository;
import ru.allteran.sellpo.repo.RepairDeviceStatusRepository;
import ru.allteran.sellpo.repo.RepairRequestRepository;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RepairService {
    @Autowired
    private RepairRequestRepository repairRepo;
    @Autowired
    private POSRepository posRepo;
    @Autowired
    private RepairDeviceStatusRepository statusRepo;

    public void createRepairRequest(RepairRequest request, User user, Map<String, String> form) {
        request.setId(UUID.randomUUID().toString());
        request.setRequestDate(new RepairDate(new Date()));

        RepairDeviceStatus newRequestStatus = statusRepo.findFirstById(RepairDeviceStatus.ID_NEW_REQUEST);

        request.setStatus(newRequestStatus);

        for (String posId : form.values()) {
            PointOfSales pos = posRepo.findFirstById(posId);
            if (pos != null) {
                request.setPosId(pos.getId());
                break;
            }
        }
        request.setSellerId(user.getId());

        //All string fields that don't have data in moment of request creation should be filled with any data and updated in future
        request.setRepairReason(request.getRequestReason());
        request.setSellDate(new RepairDate(new Date(0))); //set a default date
        request.setRepairmanComment("");

        repairRepo.save(request);
    }

    public List<RepairRequest> findAll() {
        return repairRepo.findAll();
    }

    public RepairRequest findById(String id) {
        return repairRepo.findFirstById(id);
    }

    // Uncomment next lines if you want to fill MongoDB with data of POSs
//    private static List<PointOfSales> posList = new ArrayList<>();
//
//    static {
//        posList.add(new PointOfSales(Dealer.ID_DEFAULT_DEALER, "966739", "г. Мурманск", "ул. Воровского 5/23"));
//        posList.add(new PointOfSales(Dealer.ID_DEFAULT_DEALER, "908450", "г. Мурманск", "пр-кт Ленина 50"));
//        posList.add(new PointOfSales(Dealer.ID_DEFAULT_DEALER, "854072", "г. Мурманск", "ул. Шевченко 34"));
//    }
//
//    @PostConstruct
//    public void init() {
//        posRepo.deleteAll();
//        posRepo.saveAll(posList);
//    }
//
//    private static List<RepairDeviceStatus> statusList = new ArrayList<>();
//
//    static {
//        statusList.add(new RepairDeviceStatus(RepairDeviceStatus.ID_NEW_REQUEST, RepairDeviceStatus.NEW_REQUEST));
//        statusList.add(new RepairDeviceStatus(RepairDeviceStatus.ID_REPAIRING, RepairDeviceStatus.REPAIRING));
//        statusList.add(new RepairDeviceStatus(RepairDeviceStatus.ID_DONE, RepairDeviceStatus.DONE));
//        statusList.add(new RepairDeviceStatus(RepairDeviceStatus.ID_UNDONE, RepairDeviceStatus.UNDONE));
//        statusList.add(new RepairDeviceStatus(RepairDeviceStatus.ID_PAID, RepairDeviceStatus.PAID));
//    }
//
    @PostConstruct
    public void init() {
        repairRepo.deleteAll();
//        statusRepo.deleteAll();
//        statusRepo.saveAll(statusList);
    }
}
