<#include "base.ftl">

<#macro page_head>
<title>articulo ${art.getId()}</title>
</#macro>

<#macro page_body>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Articulo</h1>
            <p class="margin-bottom"> Aqui se mostrara el articulo junto a toda la informacion que contiene</p>
        </div>
    </div>
</div>
<section>
    <div class="row">
        <div class="leftcolumn">

            <hr class="sep">
            <div>
                <center><h1>Articulo</h1></center>
            </div>
            <hr class="sep">

                <div class="borde">
                    <div class="espacio">
                        <br/>
                        <h2>${art.getTitle()}</h2>
                        <hr class="separador">

                        <#if userl??>
                            <#if userl.administrator || userl.getUsername() == art.getAuthor().getUsername()>
                            <a href="/product/del/${art.getId()?string["0"]}" class="especial"><i class="fa fa-close"></i></a>
                            <a href="/product/edit/${art.getId()?string["0"]}" class="especial"><i class="fa fa-edit"></i></a>
                            </#if>
                        </#if>

                        <h4>${art.getAuthor().getUsername()}, ${art.getDateTime()}</h4>
                        <br/>
                        <p><h4 class="stest" style="white-space: pre-wrap;">${art.getBody()}</h4></p>
                        <hr class="separador">
                        <p>
                         <#list art.getTags() as tag>
                             <a class="etiqueta"> ${tag.getTag()} </a>
                         </#list>
                        </p>
                    </div>
                </div>
            <br/>
            <center><p><a href="/" class="btn btn-ghost">Volver</a></p></center>
            <div class="borde">
                <div class="espacio">
                    <br/>
                    <h2>Comentarios</h2>
                    <hr class="separador">
                    <#if userl??>

                    <form method="POST" action="/product/addcomment/${art.getId()?string["0"]}">
                    <label for="contenido" ><h4> Comentario: </h4></label><br/>
                    <textarea id = "contenido" name="body"  class="form-control" style="width: 750px;"rows="8" placeholder="Escriba aqui su comentario" required></textarea>
                    <br/>
                    <p><button type="submit" class="btn btn-ghost">Agregar</button></p>
                    </form>

                    <hr class="separador">
                    </#if>
                    <p>
                    <#if art.getComments()?has_content>
                         <#list art.getComments() as comm>

                             <#if userl??>
                                 <#if userl.administrator || userl.getUsername() == art.getAuthor().getUsername()>
                                    <a href="/product/delc/${comm.getId()?string["0"]}" class="especial"><i class="fa fa-close"></i></a>
                                    <a href="/product/editc/${comm.getId()?string["0"]}" class="especial"><i class="fa fa-edit"></i></a>
                                 </#if>
                             </#if>

                             <h4>Autor: ${comm.getAuthor().getUsername()}</h4>
                            <p><h4 class="stest">${comm.getComment()}</h4></p>
                            <hr class="separador">
                         </#list>
                    <#else >
                    <h4>Nadie ha comentado en este articulo aun</h4>
                    </#if>
                    </p>
                </div>
            </div>

        </div>
        <div class="rightcolumn">

            <div class="borde">
                <br/>
                <center><p><a href="/product" class="btn btn-ghost">crear Articulo</a></p></center>
            </div>

            <div class="borde">
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>

            </div>

        </div>
    </div>
</section>
</#macro>