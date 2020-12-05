<#import "parts/common.ftlh" as common>
<@common.page>
    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Номер телефона</th>
                <th scope="col">Имя</th>
                <th scope="col">Права</th>
                <th scope="col">Статус</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td><a href="/user/${user.phone}">${user.phone}</a></td>
                    <td>${user.firstName} ${user.lastName}</td>
                    <td><#list user.roles as role>${role.name}<#sep>, </#list> </td>
                    <td><#if user.active>Активен<#else>Не активен</#if></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
