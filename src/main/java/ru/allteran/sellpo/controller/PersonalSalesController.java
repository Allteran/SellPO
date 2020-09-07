package ru.allteran.sellpo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.allteran.sellpo.domain.Employee;
import ru.allteran.sellpo.domain.Product;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.domain.Sale;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonalSalesController {

    @GetMapping("/personalSales")
    public String personalSales(Model model) {
        List<Sale> personalSales = new ArrayList<>();
        /**
         * Next lines of code are ugly, cuz I've hardcoded all data to test table view bootstrap
         */
        for (int i = 0; i<10; i++) {
            Sale sale = new Sale();

            Product product = new Product();
            product.setName("Товар " + i);
            product.setPrice(10+i);
            product.setReward(5+i);
            product.setType(ProductType.GI);

            sale.setProduct(product);
            personalSales.add(sale);
        }
        model.addAttribute("productTypes", ProductType.values());
        model.addAttribute("sales", personalSales);
        return "personalSales";
    }

}
