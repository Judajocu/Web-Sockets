<#include "base.ftl">

<#macro page_head>
<title>Usuarios</title>
</#macro>

<#macro page_body>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Usuarios</h1>
            <p class="margin-bottom">Aqui se mostraran la cantidad de usuarios actuales de la pagina </p>
        </div>
    </div>
</div>
<section>
    <div class="container">
        <div class="borde">
            <center>
            <h2> Usuarios registrados en la pagina</h2>
            <p class="lead"><h5>Cantidad de usuario: ${lista?size}</h5></p>
            <p>mas texto</p>
            <p>
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
            </center>
        </div>
    </div>
</section>
</#macro>