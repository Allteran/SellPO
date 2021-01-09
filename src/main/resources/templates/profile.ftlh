<#import "parts/common.ftlh" as common>
<@common.page>
    <#if (successMessage)??>
        <div class="alert alert-success" role="alert">
            ${successMessage}
        </div>
    </#if>
    <form action="/profile" method="post" name="userForm" class="form-signin m-3">
        <input type="hidden" name="userPhone" value="${user.phone}"/>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="fullname">Имя пользователя</label>
                <label class="form-control" id="fullname">${user.firstName} ${user.lastName}</label>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPhone">Номер телефона</label>
                <input type="tel" class="form-control ${(phoneError??)?string('is-invalid','')}" id="inputPhone"
                       name="phone"
                       placeholder="${user.phone}">
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (phoneError)??>
                        ${phoneError}
                    </#if>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword">Пароль</label>
                <input type="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                       id="inputPassword" name="password" placeholder="Пароль">
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (passwordError)??>
                        ${passwordError}
                    </#if>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPasswordConfirm">Подтверждение пароля</label>
                <input type="password" class="form-control ${(passwordConfirmError??)?string('is-invalid','')}"
                       id="inputPasswordConfirm" name="passwordConfirm" placeholder="Пароль еще раз">
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (passwordConfirmError)??>
                        ${passwordConfirmError}
                    </#if>
                </div>
            </div>
        </div>
        <div class="form-group col-md-6">
            <#list user.roles as role>
                <label>${role.displayName}</label>
                <div></div>
            </#list>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-outline-primary form-control" name="save" type="submit">Изменить данные</button>
    </form>
</@common.page>