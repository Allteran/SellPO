<#import "parts/common.ftlh" as common>
<@common.page>
    <body class="text-center">
    <form class="form-signin">
        <h1 class="h3 mb-3 front-weight-normal">Авторизация</h1>
        <label for="inputPhone" class="sr-only">Номер телефона</label>
        <input type="tel" name="phone" id="inputPhone" class="form-control" placeholder="79XXXXXXXXX" required
               autofocus>
        <label for="inputPassword" class="sr-only">Пароль</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Пароль" required>
        <div class="mb-3"></div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        <a class="mt-5 mb-3" href="#">Регистрация</a>
    </form>
    </body>
</@common.page>