package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.allteran.sellpo.models.Sale;
import ru.allteran.sellpo.service.SaleService;

import java.util.List;
import java.util.Map;

@Controller
public class SalesController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/sales/personal")
    public String personalSales(Model model) {
        List<Sale> personalSales = saleService.findAll();

        model.addAttribute("sales", personalSales);
        return "personal-sales";
    }

    @PostMapping("/sales/personal")
    public String saveSale(
            @RequestParam Map<String, String> form,
            @RequestParam("productName") String productName,
            @RequestParam("productPrice") double productPrice,
            Model model) {
        saleService.saveSale(form, productName, productPrice, 44444, 966739);
        List<Sale> personalSales = saleService.findAll();
        model.addAttribute("sales", personalSales);
        return "personal-sales";
    }

}
