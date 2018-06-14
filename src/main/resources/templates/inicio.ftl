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
    <div class="row">
        <div class="leftcolumn">

            <hr class="sep">
            <div>
                <center><h1>Articulos</h1></center>
            </div>
            <hr class="sep">

            <#if art??>
            <#list art as a>
                <div class="borde">
                    <div class="espacio">
                        <br/>
                        <h2>${a.getTitle()}</h2>
                        <hr class="separador">
                        <h4>${a.getAuthor().getUsername()}, ${a.getDateTime()}</h4>
                        <br/>
                        <p><h4 class="stest">${a.getBody()}</h4></p>
                        <center><p><a href="/product/${a.getId()?string["0"]}" class="btn btn-ghost">Leer más</a></p></center>
                        <hr class="separador">
                        <p>
                         <#list a.getTags() as tag>
                            <a class="etiqueta"> ${tag.getTag()} </a>
                        </#list>
                        </p>
                    </div>
                </div>
            <br/>
            </#list>
            </#if>

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