package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.Product;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.domain.Sale;
import ru.allteran.sellpo.repo.SaleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repository;

    public List<Sale> findAll() {
        return repository.findAll();
    }

    public List<Sale> findByProductType(ProductType type) {
        return repository.findByProductType(type);
    }
}
