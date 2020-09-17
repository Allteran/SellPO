package ru.allteran.sellpo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {

    public List<String> productToString() {
        List<String> list = new ArrayList<>();
        list.add(UtilService.MO_NAME);
        list.add(UtilService.MR_NAME);
        list.add(UtilService.ACCESSORY_NAME);
        return list;
    }
}
