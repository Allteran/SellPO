<#import "parts/common.ftlh" as common>
<@common.page>
    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">KPI</th>
                <th scope="col">Plan</th>
                <th scope="col">Fact</th>
                <th scope="col">%</th>
                <th scope="col">Price</th>
                <th scope="col">Actual Value</th>
                <th scope="col">Max Value</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">Сим</th>
                <td>${kpi.giPlan}</td>
                <td>${kpi.getGiFact()}</td>
                <td>0%</td>
                <td>0</td>
                <td>4457$</td>
                <td>10000$</td>
            </tr>
            <tr>
                <th scope="row">Мой разговор</th>
                <td> - </td>
                <td>${kpi.mrFact}</td>
                <td>0%</td>
                <td>0</td>
                <td>500$</td>
                <td>1000$</td>
            </tr>
            <tr>
                <th scope="row">Мой онлайн</th>
                <td> - </td>
                <td>${kpi.moFact}</td>
                <td>0%</td>
                <td>0</td>
                <td>800</td>
                <td>1800</td>
            </tr>
            <tr>
                <th scope="row">Мой онлайн+</th>
                <td> - </td>
                <td>${kpi.mopFact}</td>
                <td>0%</td>
                <td>0</td>
                <td>1100$</td>
                <td>2100$</td>
            </tr>
            <tr>
                <th scope="row">Премиум</th>
                <td> - </td>
                <td>${kpi.premiumFact}</td>
                <td>0%</td>
                <td>0</td>
                <td>200$</td>
                <td>1500$</td>
            </tr>
            <tr>
                <th scope="row">Остальные</th>
                <td> - </td>
                <td>${kpi.otherGiFact}</td>
                <td>0%</td>
                <td>0</td>
                <td>500$</td>
                <td>10000$</td>
            </tr>
            </tbody>
        </table>
    </div>
</@common.page>