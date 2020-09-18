<#import "parts/common.ftlh" as common>
<@common.page>
    <div class="form-group">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <select class="custom-select mx-2 my-2" id="inputGroupSelect01">
                    <option selected>Выберте тип товара</option>
                    <option value="1" id="mr">Мой разговор</option>
                    <option value="2" id="mo">Мой онлайн</option>
                    <option value="3" id="mop">Мой онлайн+</option>
                    <option value="4" id="premium">Премиум</option>
                    <option value="5" id="otherGi">Другие сим</option>
                    <option value="6" id="accessory">Аксессуар</option>
                    <option value="7" id="cellphone">Кнопочный телефон</option>
                    <option value="8" id="smartphone">Смартфон</option>
                    <option value="9" id="service">Услуги</option>
                    <option value="10" id="insurance">Страхование</option>
                    <option value="11" id="eset">ESET</option>
                    <option value="12" id="subs">Подписки</option>
                    <option value="13" id="wink">WINK</option>
                    <option value="14" id="rostelekom">Ростелеком</option>
                    <option value="15" id="yadd">Я.Адаптер</option>
                </select>
            </div>
            <input type="text" name="productName" class="form-control mx-2 my-2" aria-label="Введите название товара" placeholder="Введите название товара">
            <input type="text" name="productPrice" class="form-control mx-2 my-2" aria-label="Цена" placeholder="Цена">
            <button type="submit" class="btn btn-primary mx-2 my-2">Сохранить</button>
        </div>

    </div>
    </div>
    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Наименование</th>
                <th scope="col">Тип</th>
                <th scope="col">Количество</th>
                <th scope="col">Цена</th>
                <th scope="col">Премия</th>
            </tr>
            </thead>
            <tbody>
            <#list sales as sale>
                <tr>
                    <th scope="row">${sale.product.name}</th>
                    <td>${sale.product.type.name}</td>
                    <td>1</td>
                    <td>${sale.product.price}</td>
                    <td>${sale.product.reward}</td>
                </tr>
            </#list>


            </tbody>
        </table>
    </div>
</@common.page>