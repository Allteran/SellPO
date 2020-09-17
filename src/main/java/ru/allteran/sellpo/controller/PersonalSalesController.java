package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.domain.Sale;
import ru.allteran.sellpo.service.ProductService;
import ru.allteran.sellpo.service.SaleService;
import ru.allteran.sellpo.service.UtilService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonalSalesController {
    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;


    @GetMapping("/personalSales")
    public String personalSales(Model model) {
        List<Sale> personalSales = saleService.findAll();

        List<String> productTypes = productService.productToString();

        model.addAttribute("types", productTypes);
        model.addAttribute("sales", personalSales);
        return "personalSales";
    }

}
