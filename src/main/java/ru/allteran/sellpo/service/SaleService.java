package ru.allteran.sellpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.allteran.sellpo.domain.PayType;
import ru.allteran.sellpo.domain.Product;
import ru.allteran.sellpo.domain.ProductType;
import ru.allteran.sellpo.domain.Sale;
import ru.allteran.sellpo.repo.PayTypeRepository;
import ru.allteran.sellpo.repo.ProductTypeRepository;
import ru.allteran.sellpo.repo.SaleRepository;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private PayTypeRepository payTypeRepository;

    @Autowired
    private ProductService productService;

    /**
     * Uncomment next lines only if you are running this code first time on machine
     * This will create a mongo DB and fill first collections with test data
     * In future we need to avoid hardcode like here, just add button for admin to init data
     */
//    private static List<Sale> testSales = new ArrayList<>();
//
//    static {
//        for (int i = 0; i < 10; i++) {
//            Sale sale = new Sale();
//
//            Product product = new Product();
//            product.setId("ProductId+" + i);
//            product.setName("Товар №" + i);
//            product.setMaxReward(25);
//            product.setMinReward(10);
//            product.setPrice(250);
//
//            ProductType type = new ProductType();
//            type.setName("Мой онлайн");
//            type.setId("Мой онлайн" + i);
//
//            product.setType(type);
//
//            sale.setProduct(product);
//            sale.setDate("17092020");
//            sale.setEmployeeId(555547);
//            sale.setPayType(new PayType(Const.PAYTYPE_CASH_ID, Const.PAYTYPE_CASH_NAME));
//            sale.setId("Saleid+" + i);
//            sale.setPosId(966739);
//
//            testSales.add(sale);
//        }
//    }
//
//    @PostConstruct
//    public void init() {
//        repository.deleteAll();
//        repository
//                .saveAll(testSales);
//    }
    public List<Sale> findAll() {
        return repository.findAll();
    }

    public List<Sale> findByProductType(ProductType type) {
        return repository.findByProductType(type);
    }

    public List<Sale> findByDate(String date) {
        return repository.findByDate(date);
    }

    public void saveSale(Map<String, String> form, String productName, double productPrice,
                         double employeeId, int posId) {
        Sale sale = new Sale();

        Product product = new Product();

        PayType payType = new PayType();
        ProductType productType = new ProductType();
        for (String key : form.values()) {
            switch (key) {
                case Const.TYPE_MR_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_MR_ID);
                    break;
                case Const.TYPE_MO_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_MOP_ID);
                    break;
                case Const.TYPE_MOP_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_MOP_ID);
                    break;
                case Const.TYPE_PREMIUM_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_PREMIUM_ID);
                    break;
                case Const.TYPE_OTHER_GI_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_OTHER_GI_ID);
                    break;
                case Const.TYPE_ACCESSORY_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_ACCESSORY_ID);
                    break;
                case Const.TYPE_CELLPHONE_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_CELLPHONE_ID);
                    break;
                case Const.TYPE_SMARTPHONE_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_SMARTPHONE_ID);
                    break;
                case Const.TYPE_SERVICE_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_SERVICE_ID);
                    break;
                case Const.TYPE_INSURANCE_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_INSURANCE_ID);
                    break;
                case Const.TYPE_ESET_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_ESET_ID);
                    break;
                case Const.TYPE_SUBS_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_SUBS_ID);
                    break;
                case Const.TYPE_WINK_ID:
                    productType = productTypeRepository.findFirstById(Const.TYPE_WINK_ID);
                    break;
                case Const.PAYTYPE_CARD_ID:
                    payType = payTypeRepository.findFirstById(Const.PAYTYPE_CARD_ID);
                    break;
                case Const.PAYTYPE_CASH_ID:
                    payType = payTypeRepository.findFirstById(Const.PAYTYPE_CASH_ID);
                    break;
                case Const.PAYTYPE_CREDIT_ID:
                    payType = payTypeRepository.findFirstById(Const.PAYTYPE_CREDIT_ID);
                    break;
            }

        }
        product.setType(productType);

        product.setId(UUID.randomUUID().toString());
        product.setName(productName);
        product.setPrice(productPrice);
        product.setMinReward(productService.productMinReward(product));
        product.setMaxReward(productService.productMaxReward(product));

        sale.setProduct(product);
        sale.setPayType(payType);
        Date date = new Date();

        sale.setDate(date.toString());
        sale.setEmployeeId(employeeId);
        sale.setPosId(posId);

        //TODO: add PayType to Sale in future

        repository.save(sale);
    }
}
