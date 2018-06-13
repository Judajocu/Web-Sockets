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
    <section>
        <form method="POST" action="/add">
         <div class="container">
            <div class="borde">
                <br/>
                <h2> Articulo a crear</h2>
                <hr class="separador">
                    <label for="titulo" ><h4> Titulo: </h4></label><br/>
                    <input id = "titulo"name="title" type="text" style="width: 450px;" class="form-control" placeholder="Escriba aqui el titulo" required/><br/>
                <br/>
                    <label for="contenido" ><h4> Contenido: </h4></label><br/>
                    <textarea id = "contenido" name="body"  class="form-control" style="width: 950px;"rows="8" placeholder="Escriba aqui el contenido" required></textarea>
                <br/>
                    <label for="equiqueta" ><h4> Etiquetas (ejemplo: etiqueta 1, etiqueta 2, etiqueta 2,...)</h4></label><br/>
                <input id = "equiqueta"name="tag" type="text" style="width: 450px;" class="form-control" placeholder="Escriba aqui las etiquetas" required/><br/>
                <br/>
                <hr class="separador">
                <p><button type="submit" class="btn btn-ghost">Crear</button></p>
        </div>
            </div>
        </form>
    </section>
    <#else>
    <section>
        <div class="container">
            <div class="borde">
                <br/>
                <h2> Lo sentimos, usted no permiso de autor para tener accedo a esta opcion </h2>
                <p class="lead">Por favor cambie los permisos de autor para tener acceso a esta función</p>
                <br/>

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
            <br/>
            <h2> Lo sentimos, usted no esta logueado para tener accedo a esta opcion </h2>
            <p class="lead">Por favor registrarse para tener acceso a esta función</p>
            <br/>
        </div>
    </div>
    <div class="container">
        <p><a href="/" class="btn btn-ghost">Volver</a></p>
    </div>
</section>
</#if>
</center>
</#macro>