package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.PointOfSales;
import ru.allteran.sellpo.domain.RepairRequest;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.POSRepository;
import ru.allteran.sellpo.repo.RepairRequestRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RepairService {
    @Value("${repair.status.new.request}")
    private String STATUS_NEW_REQUEST;
    @Value("${repair.status.repairing}")
    private String STATUS_REPAIRING;
    @Value("${repair.status.done}")
    private String STATUS_DONE;
    @Value("${repair.status.undone}")
    private String STATUS_UNDONE;
    @Value("${repair.status.paid}")
    private String STATUS_PAID;

    @Autowired
    private RepairRequestRepository repairRepo;
    @Autowired
    private POSRepository posRepo;

    public void createRepairRequest(RepairRequest request, User user, Map<String, String> form) {
        request.setId(UUID.randomUUID().toString());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyy");
        request.setRequestDate(dateFormatter.format(new Date()));
        request.setStatus(STATUS_NEW_REQUEST);

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
}
