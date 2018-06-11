<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <@page_head/>

    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Font Awesome and Pixeden Icon Stroke icon fonts-->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/pe-icon-7-stroke.css">
    <!-- Google fonts - Roboto-->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,700">
    <!-- lightbox-->
    <link rel="stylesheet" href="css/lightbox.min.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="favicon.png">

    <!-- detalles-->
    <link rel="stylesheet" href="css/detalles.css">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>
<body>
<!-- navbar-->
<header class="header">
    <div role="navigation" class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header"><a href="/" class="navbar-brand">Articulos Premium</a>
                <div class="navbar-buttons">
                    <button type="button" data-toggle="collapse" data-target=".navbar-collapse" class="navbar-toggle navbar-btn">Menu<i class="fa fa-align-justify"></i></button>
                </div>
            </div>
            <div id="navigation" class="collapse navbar-collapse navbar-right">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/">Home</a></li>
                    <li><a href="/userlist">Usuarios</a></li>
                    <li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Dropdown item 1</a></li>
                            <li><a href="#">Dropdown item 2</a></li>
                            <li><a href="#">Dropdown item 3</a></li>
                            <li><a href="#">Dropdown item 4</a></li>
                        </ul>
                    </li>
                    <li><a href="contact.html">Contact</a></li>
                    <#if userl??>
                        <a href="#" class="btn navbar-btn btn-ghost"><h4><i class="	fa fa-user">${userl.username}</i></h4></a>
                        <a href="/logout" class="btn navbar-btn btn-ghost"><i class="fa fa-sign-in"></i>Log out</a>
                    <#else>
                <a href="/login" class="btn navbar-btn btn-ghost"><i class="fa fa-sign-in"></i>Log in</a>
                    </#if>
                </ul>

            </div>
        </div>
    </div>
</header>
<!-- *** LOGIN MODAL ***_________________________________________________________
-->

<!-- *** LOGIN MODAL END ***-->
<@page_body/>


<footer class="footer">
    <div class="footer__copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <p>&copy;2018 Trabajo grupo #...</p>
                </div>
                <div class="col-md-6">
                    <p class="credit">Codigo <a href="https://github.com/Melissa13/Http-JDBC_proyect" class="external">Repositorio</a> de github</p>
                    <!-- Please do not remove the backlink to us unless you support us at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
                </div>
            </div>
        </div>
    </div>

</footer>
<!-- Javascript files-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.cookie.js"> </script>
<script src="js/lightbox.min.js"></script>
<script src="js/front.js"></script>
<!-- Google Analytics: change UA-XXXXX-X to be your site's ID.-->
<!---->
<script>
    (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
        e=o.createElement(i);r=o.getElementsByTagName(i)[0];
        e.src='//www.google-analytics.com/analytics.js';
        r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
    ga('create','UA-XXXXX-X');ga('send','pageview');
</script>
</body>
</html>