<#import "parts/common.ftlh" as common>
<@common.page>
    <form action="/user/" method="post" class="form-signin m-3">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputPhone">Номер телефона</label>
                <input type="tel" class="form-control" id="inputPhone" name="phone" value="${user.phone}">
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword">Пароль</label>
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Пароль">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputFirstName">Имя</label>
                <input type="text" class="form-control" id="inputFirstName" name="firstName" value="${user.firstName}">
            </div>
            <div class="form-group col-md-6">
                <label for="inputLastName">Имя</label>
                <input type="text" class="form-control" id="inputLastName" name="lastName" value="${user.lastName}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="inputRoles">Права пользователя</label>
                <div class="form-group col-md-6" id="inputRoles">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="roleAdmin" name="roleAdmin">
                        <label for="roleAdmin" class="form-check-label">Администратор</label>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="roleManager" name="roleManager">
                        <label for="roleManager" class="form-check-label">Менеджер</label>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="roleUser" name="roleUser">
                        <label for="roleUser" class="form-check-label">Пользователь</label>
                    </div>
                </div>
            </div>
            <div class="form-group col-md-2">
                <label for="status">Статус пользователя</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" id="radioActive" <#if user.active>checked</#if>>
                    <label class="form-check-label" for="radioActive">Активен</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" id="radioInactive" <#if !(user.active)>checked</#if>>
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
                <button class="btn btn-outline-danger form-control" type="submit">Удалить пользователя</button>
            </div>
        </div>
    </form>
</@common.page>