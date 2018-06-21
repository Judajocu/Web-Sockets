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
</center>
</#macro>