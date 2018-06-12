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
        Username: <input name="username" type="text"/>${user.username}<br/>
        Nombre: <input name="nombre" type="text"/>${user.nombre}<br/>
        Password: <input name="password" type="text"/>${user.password}<br/>
        Autor: <input name="author" type="checkbox"/>${user.author}<br/>
        Administrador: <input name="administrator" type="checkbox"/>${user.administrator}<br/>
        <button name="Enviar" type="submit">Enviar</button>
    </form>
</center>
</#macro>