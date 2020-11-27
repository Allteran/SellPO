package ru.allteran.sellpo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.domain.EmployeeKPI;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(User user, Model model) {
        /**
         * The next part of code is super ugly, cuz I hardcoded all of output data
         * But dont worry, it's only for initial test, in next commit i'll cut it
         */
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
        model.addAttribute("kpi", kpi);
        return "main";
    }

}
