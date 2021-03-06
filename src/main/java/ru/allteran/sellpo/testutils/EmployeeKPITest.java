package ru.allteran.sellpo.testutils;

import ru.allteran.sellpo.models.User;
import ru.allteran.sellpo.models.EmployeeKPI;

public class EmployeeKPITest {
    private User user;

    private EmployeeKPI initEmplKPI() {
        EmployeeKPI kpi = new EmployeeKPI();
        kpi.setGiPlan(55);
        kpi.setMnpPlan(5);
        kpi.setInsurancePlan(5474);
        kpi.setUpsalePlan(100);
        kpi.setEsetPlan(905);
        kpi.setSubsPlan(990);
        kpi.setWinkPlan(2);
        kpi.setYaPlan(25);
        kpi.setRrsPlan(4);
        kpi.setServicePlan(3000);
        kpi.setPhonePlan(40000);
        kpi.setAccessoryPlan(50000);

        kpi.setMrFact(12);
        kpi.setMoFact(18);
        kpi.setMopFact(10);
        kpi.setPremiumFact(2);
        kpi.setOtherGiFact(0);

        kpi.setMnpFact(10);
        kpi.setInsuranceFact(5642);
        kpi.setUpsaleFact(100);
        kpi.setEsetFact(905);
        kpi.setSubsFact(500);
        kpi.setWinkFact(1);
        kpi.setYaFact(30);
        kpi.setRrsFact(3);
        kpi.setServiceFact(5190);
        kpi.setPhoneFact(81570);
        kpi.setAccessoryFact(62547);

        return kpi;
    }

    public User initEmployee(String phone, String firstName, String secondName) {

        user.setKpi(initEmplKPI());

        user.setPhone(phone);
        user.setFirstName(firstName);
        user.setLastName(secondName);
        return user;
    }
}
