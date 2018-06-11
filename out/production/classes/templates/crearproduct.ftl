<#include "base.ftl">

<#macro page_head>
<title>Crear Producto</title>
</#macro>

<#macro page_body>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Creacion de Articulos</h1>
            <p class="margin-bottom">Aqui puede crear nuevos articulos que seran mostrados en la pagina </p>
        </div>
    </div>
</div>
<center>
<#if userl??>
    <#if userl.author>
                     <h5>usuario registrado: ${userl.username}</h5>

    <section>
        <div class="container">
            <div class="borde">

                <h2> Esto es un ejemplo</h2>
                <p class="lead">Texto</p>
                <p>mas texto</p>
                <p><a class="btn btn-ghost">Read more</a></p>

            </div>
        </div>
        <div class="container">
            <p><a href="#" class="btn btn-ghost">crear</a></p>
        </div>
    </section>
    <#else>
    <section>
        <div class="container">
            <div class="borde">

                <h2> Lo sentimos, usted no permiso de autor para tener accedo a esta opcion </h2>
                <p class="lead">Por favor cambie los permisos de autor para tener acceso a esta función</p>

            </div>
        </div>
        <div class="container">
            <p><a href="/" class="btn btn-ghost">Volver</a></p>
        </div>
    </section>
    </#if>
<#else>
<section>
    <div class="container">
        <div class="borde">

            <h2> Lo sentimos, usted no esta logueado para tener accedo a esta opcion </h2>
            <p class="lead">Por favor registrarse para tener acceso a esta función</p>

        </div>
    </div>
    <div class="container">
        <p><a href="/" class="btn btn-ghost">Volver</a></p>
    </div>
</section>
</#if>
</center>
</#macro>