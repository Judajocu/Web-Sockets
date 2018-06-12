<#include "base.ftl">

<#macro page_head>
<title>Usuarios</title>
</#macro>

<#macro page_body>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Usuarios</h1>
            <p class="margin-bottom">Aqui se Administran los usuarios</p>
        </div>
    </div>
</div>
    <#if userl??>
        <#if userl.administrator?c>
                     <h5>usuario registrado: ${userl.username}</h5>

    <section>
        <center>
            <table>
                <tr><th>username</th>
                    <th>Nombre</th>
                    <th>password</th>
                    <th>administrador</th>
                    <th>promover?</th>
                    <th>Eliminar</th>
                </tr>
            <#list lista as user>
            <tr><td>${user.username}</td>
                <td>${user.nombre}</td>
                <td>${user.password}</td>
                <td>${user.administrator?string('si','no')}</td>
                <td><a href="/editaruser/${user.username}"><button name="Editar" type="submit">Editar</button></a></td>
                <td><a href="/deleteuser/${user.username}"><button name="Eliminar" type="submit">Eliminar</button></a></td>
            </tr>
            </#list>
            </table>
            <p><a href="/addUser/"><button name="Agregar Usuario" type="submit">Agregar</button></a></p>
        </center>
    </section>
        </#if>
    <#else>
<section>
    <div class="container">
        <div class="borde">

            <h2> Lo sentimos, usted no esta logueado para tener accedo a esta opcion </h2>
            <p class="lead">Por favor registrarse para tener acceso a esta función</p>

        </div>
    </div>
    <div class="container">
        <p><a href="/" class="btn btn-ghost">Volver</a></p>
    </div>
</section>
    </#if>

</#macro>