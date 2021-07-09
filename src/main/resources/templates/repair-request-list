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
                <th scope="col">Устройство</th>
                <th scope="col">Причина ремонта</th>
                <th scope="col">Статус</th>
                <th scope="col">Стоимость</th>
                <th scope="col">Клиент</th>
                <th scope="col">Контактный номер</th>
            </tr>
            </thead>
            <tbody>
            <#list requests as request>
                <tr>
                    <td><a href="/request/${request.id}">${request.requestDate.stringDate}</a></td>
                    <td><#if (request.productName)??>${request.productName}</#if></td>
                    <td><#if (request.repairReason)??>${request.repairReason}</#if></td>
                    <td><#if (request.status)??>${request.status.status}</#if></td>
                    <td><#if (request.totalPrice)??>${request.totalPrice} р.</#if></td>
                    <td><#if (request.clientName)??>${request.clientName}</#if></td>
                    <td><#if (request.clientPhone)??>${request.clientPhone}</#if></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>