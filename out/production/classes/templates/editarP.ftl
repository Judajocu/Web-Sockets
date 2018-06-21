<#include "base.ftl">

<#macro page_head>
<title>Editar articulo</title>
</#macro>

<#macro page_body>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Edicion de Articulo</h1>
            <p class="margin-bottom">Aqui se podran editar los diferentes articulo si se tiene los permisos necesarios </p>
        </div>
    </div>
</div>
<center>
    <section>
        <form method="POST" action="/edit/${art.getId()?string["0"]}">
            <div class="container">
                <div class="borde">
                    <br/>
                    <h2> Articulo a crear</h2>
                    <hr class="separador">
                    <label for="titulo" ><h4> Titulo: </h4></label><br/>
                    <input id = "titulo"name="title" type="text" style="width: 450px;" class="form-control" value="${art.getTitle()}" required/><br/>
                    <br/>
                    <label for="contenido" ><h4> Contenido: </h4></label><br/>
                    <textarea id = "contenido" name="body"  class="form-control" style="width: 950px;"rows="8" required>${art.getBody()}</textarea>
                    <br/>
                    <label for="equiqueta" ><h4> Etiquetas (ejemplo: etiqueta 1, etiqueta 2, etiqueta 2,...)</h4></label><br/>
                    <input id = "equiqueta"name="tag" type="text" style="width: 450px;" class="form-control" value="${tag}" required/><br/>
                    <br/>
                    <hr class="separador">
                    <p><button type="submit" class="btn btn-ghost">Guardar Cambios</button></p>
                </div>
            </div>
        </form>
        <center><p><a href="/product/${art.getId()?string["0"]}" class="btn btn-ghost">Volver</a></p></center>
    </section>
</center>
</#macro>