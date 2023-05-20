<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Хулигаз</title>

</head>
<body>
<h1>Хулигаз</h1>
<h3>Подробная информация о пользователе ${user.name}</h3>
<b>Номер телефона </b> ${user.phoneNumber}<br>
<b>Email </b> ${user.email}<br>
<h4>Товары пользваотеля ${user.name}</h4>
<#list products as product>
    <div>
        <b>${product.title}</b> ${product.price} руб. | <a href="/product/${product.id}">Подробнее...</a>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>