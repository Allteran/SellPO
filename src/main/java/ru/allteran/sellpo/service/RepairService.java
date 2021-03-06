package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.PointOfSales;
import ru.allteran.sellpo.domain.RepairDeviceStatus;
import ru.allteran.sellpo.domain.RepairRequest;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.POSRepository;
import ru.allteran.sellpo.repo.RepairDeviceStatusRepository;
import ru.allteran.sellpo.repo.RepairRequestRepository;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

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
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyy");
        request.setRequestDate(dateFormatter.format(new Date()));

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
        request.setSellDate("");
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
//    @PostConstruct
//    public void init() {
//        repairRepo.deleteAll();
//        statusRepo.deleteAll();
//        statusRepo.saveAll(statusList);
//    }
}
