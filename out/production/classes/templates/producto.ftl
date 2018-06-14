<#include "base.ftl">

<#macro page_head>
<title>articulo ${art.getId()}</title>
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
                        <h4>${art.getAuthor().getUsername()}, ${art.getDateTime()}</h4>
                        <br/>
                        <p><h4 class="stest">${art.getBody()}</h4></p>
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
                    <p>
                    <#if art.getComments()?has_content>
                         <#list art.getComments() as comm>
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
                <h2> Esto es un ejemplo</h2>
                <p class="lead">Texto</p>
                <p>mas texto</p>
                <p><a class="btn btn-ghost">Read more</a></p>

            </div>

        </div>
    </div>
</section>
</#macro>