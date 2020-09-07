package ru.allteran.sellpo.domain;

import java.util.List;

public enum ProductType {
    GI, MR, MO, MOP, PREMIUM, OTHER_GI, MNP, UPSALE, ACCESSORY, CELLPHONE, SMARTPHONE, SERVICE,
    INSURANCE, ESET, SUBS, WINK, ROSTELECOM, YADD;

    public String convertToString(ProductType type) {
        switch (type) {
            case MR:
                return "Мой разговор";
            case MO:
                return "Мой онлайн";
            case MOP:
                return "Мой онлайн+";
            case PREMIUM:
                return "Премиум";
            case OTHER_GI:
                return "Остальные сим";
            case UPSALE:
                return "UpSale";
            case ACCESSORY:
                return "Аксессуар";
            case CELLPHONE:
                return "Кнопочный телефон";
            case SMARTPHONE:
                return "Смартфон";
            case SERVICE:
                return "Услуги";
            case INSURANCE:
                return "Страхование";
            case ESET:
                return "ESET";
            case SUBS:
                return "Подписки";
            case WINK:
                return "WINK";
            case ROSTELECOM:
                return "Ростелеком";
            case YADD:
                return "Я.Адаптер";
            default:
                break;
        }
        return null;
    }
}
