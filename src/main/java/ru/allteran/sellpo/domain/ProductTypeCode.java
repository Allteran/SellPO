package ru.allteran.sellpo.domain;

import org.springframework.data.mongodb.core.mapping.Document;
@Document
public enum ProductTypeCode {
    GI, MR, MO, MOP, PREMIUM, OTHER_GI, MNP, UPSALE, ACCESSORY, CELLPHONE, SMARTPHONE, SERVICE,
    INSURANCE, ESET, SUBS, WINK, ROSTELECOM, YADD;

}
