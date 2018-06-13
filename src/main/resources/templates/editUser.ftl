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
    <form action="/editaruser/${index}/${user}" method="post">
        Username: <input name="username" type="text"/>${user.username}<br/>
        Nombre: <input name="nombre" type="text"/>${user.nombre}<br/>
        Password: <input name="password" type="text"/>${user.password}<br/>
        <#if user.author>
        Autor: <input name="author" type="checkbox" value="on"/><br/>
        <#else >
        Autor: <input name="author" type="checkbox"/><br/>
        </#if>
        <#if user.administrator>
        Autor: <input name="administrator" type="checkbox" value="on"/><br/>
        <#else >
        Administrator: <input name="administrator" type="checkbox"/><br/>
        </#if>
        <button name="Enviar" type="submit">Enviar</button>
    </form>
</center>
</#macro>