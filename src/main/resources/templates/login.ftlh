<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <title>SellPO</title>
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: -webkit-box;
            display: flex;
            -ms-flex-align: center;
            -ms-flex-pack: center;
            -webkit-box-align: center;
            align-items: center;
            -webkit-box-pack: center;
            justify-content: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }
        div {
            word-wrap: normal   ;
        }

        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input[type="tel"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
        </style>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
</head>
<body>
<body class="text-center">
<form class="form-signin" action="/login" method="post">
    <#if (alertMessage)??>
        <div class="mb-3">
            <div class="alert alert-success" role="alert">
                ${alertMessage}
            </div>
        </div>
    </#if>
    <h1 class="h3 mb-3 front-weight-normal">Авторизация</h1>
    <label for="inputPhone" class="sr-only">Номер телефона</label>
    <input type="tel" name="username" id="inputPhone" class="form-control ${(loginError??)?string('is-invalid', '')}"
           placeholder="79XXXXXXXXX" required
           autofocus>
    <label for="inputPassword" class="sr-only">Пароль</label>
    <input type="password" id="inputPassword" name="password"
           class="form-control ${(loginError??)?string('is-invalid', '')}" placeholder="Пароль" required>
    <div class="text-left invalid-feedback m-1 ml-1">
        <#if (loginError)??>
            ${loginError}
        </#if>
    </div>
    <div class="mb-3"></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    <a class="mt-8 mb-3" href="/registration">Регистрация</a>
</form>
</body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>

</body>
</html>