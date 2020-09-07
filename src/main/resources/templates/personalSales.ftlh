<#import "parts/common.ftlh" as common>
<@common.page>
    <div>
        <!-- Need to fix bug cuz ${type.convertToString(type)} dont work, return null, also table is not rendering -->
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="productType" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Выберите тип товара
            </button>
            <div class="dropdown-menu" aria-labelledby="productType">
                <#list productTypes as type>
                    <button class="dropdown-item" type="button">${type.convertToString(type)}</button>
                </#list>
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
                <td>${sale.product.type}</td>
                <td>1</td>
                <td>${sale.product.price}</td>
                <td>${sale.product.reward}</td>
            </tr>
        </#list>


        </tbody>
    </table>
    </div>
</@common.page>