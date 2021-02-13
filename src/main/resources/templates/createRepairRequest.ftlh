<#import "parts/common.ftlh" as common>
<@common.page>
    <form action="/createRepairRequest" method="post" name="repairRequest" class="form-signin m-3">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputType">Тип устройства</label>
                <input type="text" class="form-control ${(productTypeError??)?string('is-invalid','')}" id="inputType"
                       name="productType"
                       placeholder="Смартфон, ноутбук...">
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (productTypeError)??>
                        ${productTypeError}
                    </#if>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="inputProduct">Модель устройства</label>
                <input type="text" class="form-control ${(productNameError??)?string('is-invalid','')}"
                       id="inputProduct" name="productName"
                       placeholder="iPhone X, Samsung S10...">
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (productNameError)??>
                        ${productNameError}
                    </#if>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputRequestReason">Неисправность со слов клиента</label>
                <textarea class="form-control ${(requestReasonError??)?string('is-invalid', '')}"
                          id="inputRequestReason" name="requestReason" rows="3"></textarea>
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (requestReasonError)??>
                        ${requestReasonError}
                    </#if>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="inputEquipSet">Комплектация</label>
                <textarea class="form-control ${(equipSetError??)?string('is-invalid', '')}" id="inputEquipSet"
                          name="equipSet" rows="3"></textarea>
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (equipSetError)??>
                        ${equipSetError}
                    </#if>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputClientName">Имя клиента</label>
                <input type="text" class="form-control ${(clientNameError??)?string('is-invalid', '')}"
                       id="inputClientName" name="clientName" placeholder="Иванов Иван Иванович">
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (clientNameError)??>
                        ${clientNameError}
                    </#if>
                </div>
            </div>
            <div class="form-group col-md-6">
                <label for="inputClientPhone">Номер телефона клиента</label>
                <input type="tel" class="form-control ${(clientPhoneError??)?string('is-invalid', '')}"
                       id="inputClientPhone" name="clientPhone" placeholder="79XXXXXXXXX">
                <div class="text-left invalid-feedback m-1 ml-1">
                    <#if (clientPhoneError)??>
                        ${clientPhoneError}
                    </#if>
                </div>
            </div>
        </div>
        </div>
        <div class="form-row">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-outline-primary form-control" name="createRequest" type="submit">Принять в
                ремонт
            </button>
        </div>
    </form>
</@common.page>