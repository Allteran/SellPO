package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.allteran.sellpo.domain.Product;
import ru.allteran.sellpo.domain.Sale;
import ru.allteran.sellpo.service.ProductService;
import ru.allteran.sellpo.service.SaleService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class PersonalSalesController {
    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;


    @GetMapping("/personalSales")
    public String personalSales(Model model) {
        List<Sale> personalSales = saleService.findAll();

        model.addAttribute("sales", personalSales);
        return "personalSales";
    }

    @PostMapping("/personalSales")
    public String saveSale(
            @RequestParam Map<String, String> form,
            @RequestParam("productName") String productName,
            @RequestParam("productPrice") String productPrice,
            Model model) {
        saleService.saveSale(form, productName, Double.parseDouble(productPrice), 44444, 966739);
        List<Sale> personalSales = saleService.findAll();
        model.addAttribute("sales", personalSales);
        return "personalSales";
    }

}
