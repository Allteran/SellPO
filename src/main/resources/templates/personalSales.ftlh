<#import "parts/common.ftlh" as common>
<@common.page>
    <div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <select class="custom-select mx-2 my-2" id="inputGroupSelect01">
                    <option selected>Выберте тип товара</option>
                    <option value="1">Мой разговор</option>
                    <option value="2">Мой онлайн</option>
                    <option value="3">Мой онлайн+</option>
                    <option value="4">Премиум</option>
                    <option value="5">Другие сим</option>
                    <option value="6">Аксессуар</option>
                    <option value="7">Кнопочный телефон</option>
                    <option value="8">Смартфон</option>
                    <option value="9">Услуги</option>
                    <option value="10">Страхование</option>
                    <option value="11">ESET</option>
                    <option value="12">Подписки</option>
                    <option value="13">WINK</option>
                    <option value="14">Ростелеком</option>
                    <option value="15">Я.Адаптер</option>
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