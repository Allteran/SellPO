package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.Product;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.domain.Sale;
import ru.allteran.sellpo.repo.SaleRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repository;

    private static List<Sale> testSales = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            Sale sale = new Sale();

            Product product = new Product();
            product.setId("ProductId+" + i);
            product.setName("Товар №" + i);
            product.setReward(25);
            product.setPrice(250);

            ProductType type = new ProductType();
            type.setName("Мой онлайн");
            type.setId("Мой онлайн" + i);

            product.setType(type);

            sale.setProduct(product);
            sale.setDate("17092020");
            sale.setEmployeeId(555547);
            sale.setPayType(12);
            sale.setId("Saleid+" + i);
            sale.setPosId(966739);

        }
    }

    @PostConstruct
    public void init() {
        repository.saveAll(testSales);
    }

    public List<Sale> findAll() {
        return repository.findAll();
    }

    public List<Sale> findByProductType(ProductType type) {
        return repository.findByProductType(type);
    }

    public List<Sale> findByDate(String date) {
        return repository.findByDate(date);
    }
}
