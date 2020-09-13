package ru.allteran.sellpo.service;

import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.domain.ProductTypeCode;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilService {
    public static final String MR_NAME =  "Мой разговор";
    public static final String MO_NAME = "Мой онлайн";
    public static final String MOP_NAME = "Мой онлайн+";
    public static final String PREMIUM_NAME = "Премиум";
    public static final String OTHER_GI_NAME = "Остальные сим";
    public static final String UPSALE_NAME = "UpSale";
    public static final String ACCESSORY_NAME = "Аксессуар";
    public static final String CELLPHONE_NAME = "Кнопочный телефон";
    public static final String SMARTPHONE_NAME = "Смартфон";
    public static final String SERVICE_NAME = "Услуги";
    public static final String INSURANCE_NAME = "Страхование";
    public static final String ESET_NAME = "ESET";
    public static final String SUBS_NAME = "Подписки";
    public static final String WINK_NAME = "WINK";
    public static final String ROSTELECOM_NAME = "Ростелеком";
    public static final String YADD_NAME="Я.Адаптер";

    public List<String> typesToString (List<ProductType> typeList) {
        List<String> typesString = new ArrayList<>();
        for (ProductType type : typeList) {
            typesString.add(type.getName());
        }
        return typesString;
    }
}
