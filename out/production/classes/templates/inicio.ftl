<#include "base.ftl">

<#macro page_head>
<title>Inicio</title>
</#macro>

<#macro page_body>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Practica #3</h1>
            <p class="margin-bottom">Juan Joa y Melissa </p>
            <p><a href="#" class="btn btn-white">Learn more</a></p>
        </div>
    </div>
</div>
<section>
    <div class="container">
        <div class="borde">
            <h2> Esto es un ejemplo</h2>
            <p class="lead">Texto</p>
            <p>mas texto</p>
            <p><a class="btn btn-ghost">Read more</a></p>
            <h3>pagina de listas</h3>
            <p>
            <h5>Cantidad de usuario: ${lista?size}</h5>
            <table>
                <tr><th>username</th><th>Nombre</th><th>password</th></tr>
                <#list lista as user>
                <tr><td>${user.username}</td><td>${user.nombre}</td><td>${user.password}</td></tr>
                </#list>
            </table>
            </p>
             <#if userl??>
                 <h5>usuario registrado: ${userl.username}</h5>
             </#if>

        </div>
    </div>
</section>
</#macro>