package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.allteran.sellpo.domain.Product;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.domain.ProductTypeCode;
import ru.allteran.sellpo.domain.Sale;
import ru.allteran.sellpo.service.UtilService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonalSalesController {
    @Autowired
    private UtilService utilService;

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

            ProductType productType = new ProductType();
            productType.setName(UtilService.MO_NAME);
            productType.setTypeCode(ProductTypeCode.MO);

            product.setType(productType);

            sale.setProduct(product);
            personalSales.add(sale);
        }

        List<ProductType> productTypeList = allProductTypes();
        List<String> typeListString = utilService.typesToString(productTypeList);

        model.addAttribute("types", typeListString);
        model.addAttribute("sales", personalSales);
        return "personalSales";
    }

    private List<ProductType> allProductTypes() {
        List<ProductType> types = new ArrayList<>();
        ProductType mr = new ProductType();
        mr.setName(UtilService.MR_NAME);
        mr.setTypeCode(ProductTypeCode.MR);

        types.add(mr);

        ProductType mo = new ProductType();
        mo.setName(UtilService.MO_NAME);
        mo.setTypeCode(ProductTypeCode.MO);

        types.add(mo);

        ProductType mop = new ProductType();
        mop.setName(UtilService.MOP_NAME);
        mop.setTypeCode(ProductTypeCode.MOP);

        types.add(mop);

        ProductType premium = new ProductType();
        premium.setName(UtilService.PREMIUM_NAME);
        premium.setTypeCode(ProductTypeCode.PREMIUM);

        types.add(premium);

        ProductType otherGi = new ProductType();
        otherGi.setName(UtilService.OTHER_GI_NAME);
        otherGi.setTypeCode(ProductTypeCode.OTHER_GI);

        types.add(otherGi);

        ProductType upSale = new ProductType();
        upSale.setName(UtilService.UPSALE_NAME);
        upSale.setTypeCode(ProductTypeCode.UPSALE);

        types.add(upSale);

        ProductType accessory = new ProductType();
        accessory.setName(UtilService.ACCESSORY_NAME);
        accessory.setTypeCode(ProductTypeCode.ACCESSORY);

        types.add(accessory);

        ProductType cellphone = new ProductType();
        cellphone.setName(UtilService.CELLPHONE_NAME);
        cellphone.setTypeCode(ProductTypeCode.CELLPHONE);

        types.add(cellphone);

        ProductType smartphone = new ProductType();
        smartphone.setName(UtilService.SMARTPHONE_NAME);
        smartphone.setTypeCode(ProductTypeCode.SMARTPHONE);

        types.add(smartphone);

        return types;
    }



}
