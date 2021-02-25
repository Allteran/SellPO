<#import "parts/common.ftlh" as common>
<@common.page>
    <div>
        <#if (successMessage)??>
            <div class="mb-3">
                <div class="alert alert-success" role="alert">
                    ${successMessage}
                </div>
            </div>
        </#if>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Дата приема</th>
                <th scope="col">Тип устройства</th>
                <th scope="col">Модель</th>
                <th scope="col">Причина ремонта</th>
                <th scope="col">Стоимость ремонта</th>
                <th scope="col">Клиент</th>
                <th scope="col">Контактный номер</th>
            </tr>
            </thead>
            <tbody>
            <#list requests as request>
                <td><a href="/request/${request.id}">${request.id}</a></td>
                <td><#if (request.productType)??></#if>${request.productType}</td>
                <td><#if (request.productName)??></#if>${request.productName}</td>
                <td><#if (request.repairReason)??></#if>${request.repairReason}</td>
                <td><#if (request.totalPrice)??></#if>${request.totalPrice} р.</td>
                <td><#if (request.clientName)??></#if>${request.clientName}</td>
                <td><#if (request.clientPhone)??></#if>${request.clientPhone}</td>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>