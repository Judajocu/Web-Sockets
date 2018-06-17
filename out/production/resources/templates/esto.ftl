<#include "base.ftl">

<#macro page_head>
<title>Usuarios</title>
</#macro>

<#macro page_body>
<center>
<div class="jumbotron main-jumbotron">
    <div class="container">
        <div class="content">
            <h1>Usuarios</h1>
            <p class="margin-bottom">Aqui se Administran los usuarios</p>
        </div>
    </div>
</div>
        <section>
    <div class="container">
        <div class="borde">
            <br/>
            <h2> Usuarios registrados</h2>
            <hr class="separador">
            <table>
                <tr><th>username</th>
                    <th>Nombre</th>
                    <th>Password</th>
                    <th>Autor</th>
                    <th>Administrador</th>
                    <th>Promover?</th>
                    <th>Eliminar</th>
                </tr>
            <#list lista as user>
            <tr><td>${user.username}</td>
                <td>${user.nombre}</td>
                <td>${user.password}</td>
                <td>${user.author?string('si','no')}</td>
                <td>${user.administrator?string('si','no')}</td>
                <td><a href="userlist/editaruserForm/${user.username}"><button name="Editar" type="submit" class="btn btn-ghost">Editar</button></a></td>
                <td><a href="userlist/deleteuser/${user.username}"><button name="Eliminar" type="submit" class="btn btn-ghost">Eliminar</button></a></td>
            </tr>
            </#list>
            </table>
            <hr class="separador">
            <p><a href="userlist/addUserForm/"><button name="Agregar Usuario" type="submit" class="btn btn-ghost">Agregar</button></a></p>
        </div>
    </div>
    </section>

</center>
</#macro>