package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.PayType;
import ru.allteran.sellpo.domain.Product;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.repo.PayTypeRepository;
import ru.allteran.sellpo.repo.ProductTypeRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private PayTypeRepository payTypeRepository;

    /**
     * Uncomment next lines only if you are running this code first time on machine
     * This will create a mongo DB and fill collections with static data
     * In future we need to avoid hardcode like here, just add button for admin to init static data
     * static data are PayType and ProductType
     */
//    private static List<ProductType> typeList = new ArrayList<>();
//
//    static {
//        typeList.add(new ProductType(Const.TYPE_MR_ID,Const.TYPE_MR_NAME));
//        typeList.add(new ProductType(Const.TYPE_MO_ID, Const.TYPE_MO_NAME));
//        typeList.add(new ProductType(Const.TYPE_MOP_ID, Const.TYPE_MOP_NAME));
//        typeList.add(new ProductType(Const.TYPE_PREMIUM_ID, Const.TYPE_PREMIUM_NAME));
//        typeList.add(new ProductType(Const.TYPE_OTHER_GI_ID, Const.TYPE_OTHER_GI_NAME));
//        typeList.add(new ProductType(Const.TYPE_ACCESSORY_ID, Const.TYPE_ACCESSORY_NAME));
//        typeList.add(new ProductType(Const.TYPE_CELLPHONE_ID, Const.TYPE_CELLPHONE_NAME));
//        typeList.add(new ProductType(Const.TYPE_SMARTPHONE_ID, Const.TYPE_SMARTPHONE_NAME));
//        typeList.add(new ProductType(Const.TYPE_SERVICE_ID, Const.TYPE_SERVICE_NAME));
//        typeList.add(new ProductType(Const.TYPE_INSURANCE_ID, Const.TYPE_INSURANCE_NAME));
//        typeList.add(new ProductType(Const.TYPE_ESET_ID, Const.TYPE_ESET_NAME));
//        typeList.add(new ProductType(Const.TYPE_SUBS_ID, Const.TYPE_SUBS_NAME));
//        typeList.add(new ProductType(Const.TYPE_WINK_ID, Const.TYPE_WINK_NAME));
//    }
//
//    private static List<PayType> payTypeList = new ArrayList<>();
//
//    static {
//        payTypeList.add(new PayType(Const.PAYTYPE_CREDIT_ID, Const.PAYTYPE_CREDIT_NAME));
//        payTypeList.add(new PayType(Const.PAYTYPE_CASH_ID, Const.PAYTYPE_CASH_NAME));
//        payTypeList.add(new PayType(Const.PAYTYPE_CARD_ID, Const.PAYTYPE_CARD_NAME));
//    }
//
//
//    @PostConstruct
//    public void init() {
//        productTypeRepository.saveAll(typeList);
//        payTypeRepository.saveAll(payTypeList);
//    }

    public double productMaxReward(Product product) {
        double reward;
        ProductType type = product.getType();
        switch (type.getId()) {
            case Const.TYPE_MR_ID:
                reward = 100;
                break;
            case Const.TYPE_MO_ID:
                reward = 200;
                break;
            case Const.TYPE_PREMIUM_ID:
                reward = 400;
                break;
            case Const.TYPE_OTHER_GI_ID:
                reward = 0;
                break;
            case Const.TYPE_ACCESSORY_ID:
                reward = product.getPrice() * 0.2;
                break;
            case Const.TYPE_CELLPHONE_ID:
            case Const.TYPE_SMARTPHONE_ID:
                reward = product.getPrice() * 0.03;
                break;
            case Const.TYPE_SERVICE_ID:
                reward = product.getPrice() * 0.5;
                break;
            case Const.TYPE_INSURANCE_ID:
            case Const.TYPE_ESET_ID:
                reward = product.getPrice() * 0.4;
                break;
            case Const.TYPE_SUBS_ID:
            case Const.TYPE_WINK_ID:
                reward = product.getPrice() * 0.1;
                break;
            default:
                reward = 0;
        }

        return reward;
    }

    public double productMinReward(Product product) {
        double reward;
        ProductType type = product.getType();
        switch (type.getId()) {
            case Const.TYPE_MR_ID:
            case Const.TYPE_MO_ID:
            case Const.TYPE_PREMIUM_ID:
                reward = 100;
                break;
            case Const.TYPE_ACCESSORY_ID:
            case Const.TYPE_SUBS_ID:
            case Const.TYPE_WINK_ID:
                reward = product.getPrice() * 0.1;
                break;
            case Const.TYPE_CELLPHONE_ID:
            case Const.TYPE_SMARTPHONE_ID:
                reward = product.getPrice() * 0.02;
                break;
            case Const.TYPE_SERVICE_ID:
                reward = product.getPrice() * 0.2;
                break;
            case Const.TYPE_INSURANCE_ID:
            case Const.TYPE_ESET_ID:
                reward = product.getPrice() * 0.4;
                break;
            case Const.TYPE_OTHER_GI_ID:
            default:
                reward = 0;
        }

        return reward;
    }
}
