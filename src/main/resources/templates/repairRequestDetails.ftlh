<#import "parts/common.ftlh" as common>
<@common.page>

    <form action="/repair" method="post" name="repairRequest" class="form-singin m-3">
        <div class="form-row" style="margin-top: 10px">
            <div class="col-md-2">
                <label for="inputRequestDate">Дата заявки</label>
                <input type="text" readonly class="form-control-plaintext" id="inputRequestDate" name="requestDate"
                       value="${request.requestDate}">
            </div>
            <div class="col-md-4">
                <label for="inputType">Тип устройства</label>
                <input type="text" class="form-control" id="inputType" name="productType"
                       value="${request.productType}">
            </div>
            <div class="col-md-6">
                <label for="inputProductName">Модель устройства</label>
                <input type="text" class="form-control" id="inputProductName" name="productName"
                       value="${request.productName}">
            </div>
        </div>
        <div class="form-row" style="margin-top: 10px">
            <div class="col">
                <label for="inputClientName">Имя клиента</label>
                <input type="text" class="form-control" id="inputClientName" name="clientName"
                       value="${request.clientName}">
            </div>
            <div class="col">
                <label for="inputClientPhone">Контактный номер</label>
                <input type="tel" class="form-control" id="inputClientPhone" name="clientPhone"
                       value="${request.clientPhone}">
            </div>
        </div>
        <div class="form-row" style="margin-top: 10px">
            <div class="col">
                <label for="inputEquipSet">Комплектация</label>
                <textarea class="form-control" id="inputEquipSet" name="equipSet"
                          rows="2">${request.equipSet}</textarea>
            </div>
            <div class="col">
                <label for="inputRequestReason">Причина обращения</label>
                <textarea id="inputRequestReason" class="form-control" name="requestReason"
                          rows="2">${request.requestReason}</textarea>
            </div>
            <div class="col">
                <label for="inputRepairReason">Детали ремонта</label>
                <textarea class="form-control" id="inputRepairReason" name="repairReason"
                          rows="2"><#if (request)??>${request.repairReason}</#if></textarea>
            </div>
        </div>
        <div class="form-row" style="margin-top: 10px">
            <label for="inputDeviceState">Статус ремонта</label>
            <select class="custom-select" id="inputDeviceState" name="deviceStatus">
                <#list statusList as status>
                    <option <#if request.status.id == status.id> selected</#if>
                            value="${status.id}">${status.status}</option>
                </#list>
            </select>
        </div>
        <#--TODO: FINISH THIS PAGE-->
    </form>
</@common.page>