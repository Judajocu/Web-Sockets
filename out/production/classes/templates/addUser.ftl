<#include "base.ftl">

<#macro page_head>
<title>Usuarios registro</title>
</#macro>

<#macro page_body>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Registro de Usuarios</h1>
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
    <form action="/AddUser/" method="post">
        Username: <input name="username" type="text"/><br/>
        Nombre: <input name="nombre" type="text"/><br/>
        Password: <input name="password" type="text"/><br/>
        Autor: <input name="author" type="checkbox"/><br/>
        Administrador: <input name="administrator" type="checkbox"/><br/>
        <hr class="separador">
        <button name="Enviar" type="submit" class="btn btn-ghost">Enviar</button>
    </form>
        </div>
    </div>
        <a href="/userlist" class="btn btn-ghost">Volver</a>
    </section>
</center>
</#macro>