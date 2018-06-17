<#include "base.ftl">

<#macro page_head>
<title>Editar comentario</title>
</#macro>

<#macro page_body>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Edicion de comentario</h1>
            <p class="margin-bottom">Aqui se podran editar los comentarios si se tiene los permisos necesarios </p>
        </div>
    </div>
</div>
<center>
<#if userl??>
    <#if userl.author || userl.administrator>
    <section>
        <form method="POST" action="/editc/${c.getId()?string["0"]}">
            <div class="container">
                <div class="borde">
                    <br/>
                    <h2> Comentario</h2>
                    <hr class="separador">
                    <h4>Autor: ${c.getAuthor().getUsername()}</h4>
                    <label for="contenido" ><h4> Contenido: </h4></label><br/>
                    <textarea id = "contenido" name="body"  class="form-control" style="width: 950px;"rows="8" required>${c.getComment()}</textarea>
                    <br/>
                    <hr class="separador">
                    <p><button type="submit" class="btn btn-ghost">Guardar Cambios</button></p>
                </div>
            </div>
        </form>
        <center><p><a href="/product/${c.getProduct().getId()?string["0"]}" class="btn btn-ghost">Volver</a></p></center>
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