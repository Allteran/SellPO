<#import "parts/common.ftlh" as common>
<@common.page>
    <div>
        <#if (errorMessage)??>
            <div class="alert alert-danger" role="alert">
                ${errorMessage}
            </div>
        </#if>
        <#if (successMessage)??>
            <div class="alert alert-success" role="alert">
                ${successMessage}
            </div>
        </#if>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Номер телефона</th>
                <th scope="col">Имя</th>
                <th scope="col">Права</th>
                <th scope="col">Дилер</th>
                <th scope="col">Статус</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td><a href="/edit/${user.phone}">${user.phone}</a></td>
                    <td>${user.firstName} ${user.lastName}</td>
                    <td><#if user.roles??><#list user.roles as role>${role.name}<#sep>, </#list></#if> </td>
                    <td><#if user.dealer??>${user.dealer.name}</#if></td>
                    <td><#if user.active>Активен<#else>Не активен</#if></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@common.page>
