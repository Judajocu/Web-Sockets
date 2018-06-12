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
    <form action="/editaruser/${index}" method="post">
        Username: <input name="username" type="text"/><br/>
        Nombre: <input name="nombre" type="text"/><br/>
        Password: <input name="password" type="text"/><br/>
        Autor: <input name="author" type="checkbox"/><br/>
        Administrador: <input name="administrator" type="checkbox"/><br/>
        <button name="Enviar" type="submit">Enviar</button>
    </form>
</center>
</#macro>