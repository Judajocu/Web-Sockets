<#include "base.ftl">

<#macro page_head>
<title>Login</title>
</#macro>

<#macro page_body>
<section>
    <div class="container">
        <div class="borde">
            <br/>
            <center><h2> Iniciar sesion </h2></center>
            <br/>
                            <form  method="post">
                                <div class="form-group">
                                    <input  type="text" placeholder="username" class="form-control" name="username">
                                </div>
                                <div class="form-group">
                                    <input  type="password" placeholder="password" class="form-control" name="pass">
                                </div>
                                <p class="text-center">
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</button>
                                </p>
                            </form>
            </div>
    </div>
</section>
</#macro>