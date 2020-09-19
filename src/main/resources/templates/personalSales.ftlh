<#import "parts/common.ftlh" as common>
<@common.page>
    <form action="/personalSales" method="post">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <select class="custom-select mx-2 my-2" name="inputGroupSelect01">
                    <option selected>Выберте тип товара</option>
                    <option value="mrid">Мой разговор</option>
                    <option value="moid">Мой онлайн</option>
                    <option value="mopid">Мой онлайн+</option>
                    <option value="premiumid">Премиум</option>
                    <option value="othergiid">Другие сим</option>
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
            <input type="text" name="productName" class="form-control mx-2 my-2" aria-label="Введите название товара"
                   placeholder="Введите название товара">
            <input type="text" name="productPrice" class="form-control mx-2 my-2" aria-label="Цена" placeholder="Цена">
            <button type="submit" class="btn btn-primary mx-2 my-2">Сохранить</button>
        </div>

    </form>
    </div>
    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Наименование</th>
                <th scope="col">Тип</th>
                <th scope="col">Количество<</th>
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