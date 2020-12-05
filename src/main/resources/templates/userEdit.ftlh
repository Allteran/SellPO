<#import "parts/common.ftlh" as common>
<@common.page>
    <form action="/user/{user}" method="post" name="userForm" class="form-signin m-3">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputPhone">Номер телефона</label>
                <input type="tel" class="form-control" id="inputPhone" name="phone" value="${user.phone}"
                       placeholder="${user.phone}">
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (phoneError)??>
                        ${phoneError}
                    </#if>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword">Пароль</label>
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Пароль">
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (passwordError)??>
                        ${passwordError}
                    </#if>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputFirstName">Имя</label>
                <input type="text" class="form-control" id="inputFirstName" name="firstName"
                       value="${user.firstName}">
            </div>
            <div class="form-group col-md-6">
                <label for="inputLastName">Имя</label>
                <input type="text" class="form-control" id="inputLastName" name="lastName"
                       value="${user.lastName}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-2">
                <label>Права пользователя</label>
                <#list roles as role>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="roleCheckbox" name="${role.name}"
                               <#list user.roles as uRole>
                                   <#if role.id == uRole.id>checked</#if>
                               </#list>
                        <label for="roleCheckbox" class="form-check-label">${role.name}</label>
                    </div>
                </#list>
            </div>
            <div class="form-group col-md-2">
                <label>Статус пользователя</label>
                <div class="form-check">
                    <input class="form-check-input" name="radioActive" type="radio" id="radioActive"
                           <#if user.active>checked</#if>>
                    <label class="form-check-label" for="radioActive">Активен</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" name="radioActive" type="radio" id="radioInactive"
                           <#if !(user.active)>checked</#if>>
                    <label class="form-check-label" for="radioInactive">Не активен</label>
                </div>
            </div>
        </div>
        <div class="form-row">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div class="form-group col-md-6">
                <button class="btn btn-outline-primary form-control" name="save" type="submit">Сохранить</button>
            </div>
            <div class="form-group col-md-6">
                <button class="btn btn-outline-danger form-control" name="delete" type="submit">Удалить пользователя
                </button>
            </div>
        </div>
    </form>
</@common.page>