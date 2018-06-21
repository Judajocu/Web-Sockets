<#include "base.ftl">

<#macro page_head>
<title>Usuarios</title>
</#macro>

<#macro page_body>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Usuarios</h1>
        </div>
    </div>
</div>
<center>
    <section>
        <div class="container">
            <div class="borde">
                <br/>
                <h2> Creacion de usuario</h2>
                <hr class="separador">
    <form action="/editaruser/${index}/${user}" method="post">
        Username: <input name="username" type="text" value="${user.username}"/><br/>
        Nombre: <input name="nombre" type="text" value="${user.nombre}"/><br/>
        Password: <input name="password" type="text" value="${user.password}"/><br/>
        <#if user.author>
        Autor: <input name="author" type="checkbox" checked/><br/>
        <#else >
        Autor: <input name="author" type="checkbox"/><br/>
        </#if>
        <#if user.administrator>
        Administrator: <input name="administrator" type="checkbox" checked/><br/>
        <#else >
        Administrator: <input name="administrator" type="checkbox"/><br/>
        </#if>
        <hr class="separador">
        <button name="Enviar" type="submit" class="btn btn-ghost">Enviar</button>
    </form>
            </div>
        </div>
        <a href="/userlist" class="btn btn-ghost">Volver</a>
    </section>
</center>
</#macro>