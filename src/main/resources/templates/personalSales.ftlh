<#import "parts/common.ftlh" as common>
<@common.page>
    <form action="/personalSales" method="post">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <select class="custom-select mx-2 my-2" name="inputGroupSelect01">
                    <option selected value="mrid">SIM: Мой разговор</option>
                    <option value="moid">SIM: Мой онлайн</option>
                    <option value="mopid">SIM: Мой онлайн+</option>
                    <option value="premiumid">SIM: Премиум</option>
                    <option value="othergiid">SIM: Другие сим</option>
                    <option value="accessoryid">Аксессуар</option>
                    <option value="cellphoneid">Кнопочный телефон</option>
                    <option value="smartphoneid">Смартфон</option>
                    <option value="serviceid">Услуги</option>
                    <option value="insuranceid">Страхование</option>
                    <option value="esetid">ESET</option>
                    <option value="subsid">Подписки</option>
                    <option value="winkid">WINK</option>
                </select>
            </div>
            <form class="needs-validation" novalidate>
                    <input type="text" name="productName"
                           class="form-control mx-2 my-2"
                           placeholder="Введите название товара" required>
                    <div class="invalid-feedback">
                        Введите название товара
                    </div>
                    <input type="text" name="productPrice"
                           class="form-control mx-2 my-2"
                           placeholder="Цена" required>
                    <div class="invalid-feedback">
                        Введите цену товара
                    </div>
                    <button type="submit" class="btn btn-primary mx-2 my-2">Сохранить</button>
            </form>
        </div>

    </form>
    </div>
    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Наименование</th>
                <th scope="col">Тип</th>
                <th scope="col">Количество</th>
                <th scope="col">Цена</th>
                <th scope="col">Макс. бонус</th>
                <th scope="col">Мин. бонус</th>
            </tr>
            </thead>
            <tbody>
            <#list sales as sale>
                <tr>
                    <th scope="row">${sale.product.name}</th>
                    <td>${sale.product.type.name}</td>
                    <td>1</td>
                    <td>${sale.product.price} р.</td>
                    <td>${sale.product.maxReward} р.</td>
                    <td>${sale.product.minReward} р.</td>
                </tr>
            </#list>


            </tbody>
        </table>
    </div>
</@common.page>