<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Inicio</title>
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
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>
<body>
<!-- navbar-->
<header class="header">
    <div role="navigation" class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header"><a href="index.html" class="navbar-brand">Articulo Premium</a>
                <div class="navbar-buttons">
                    <button type="button" data-toggle="collapse" data-target=".navbar-collapse" class="navbar-toggle navbar-btn">Menu<i class="fa fa-align-justify"></i></button>
                </div>
            </div>
            <div id="navigation" class="collapse navbar-collapse navbar-right">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.html">Home</a></li>
                    <li><a href="text.html">Text page</a></li>
                    <li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Dropdown item 1</a></li>
                            <li><a href="#">Dropdown item 2</a></li>
                            <li><a href="#">Dropdown item 3</a></li>
                            <li><a href="#">Dropdown item 4</a></li>
                        </ul>
                    </li>
                    <li><a href="contact.html">Contact</a></li>
                </ul><a href="#" data-toggle="modal" data-target="#login-modal" class="btn navbar-btn btn-ghost"><i class="fa fa-sign-in"></i>Log in</a>
            </div>
        </div>
    </div>
</header>
<!-- *** LOGIN MODAL ***_________________________________________________________
-->
<div id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true" class="modal fade">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close">×</button>
                <h4 id="Login" class="modal-title">Customer login</h4>
            </div>
            <div class="modal-body">
                <form action="customer-orders.html" method="post">
                    <div class="form-group">
                        <input id="email_modal" type="text" placeholder="email" class="form-control">
                    </div>
                    <div class="form-group">
                        <input id="password_modal" type="password" placeholder="password" class="form-control">
                    </div>
                    <p class="text-center">
                        <button type="button" class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</button>
                    </p>
                </form>
                <p class="text-center text-muted">Not registered yet?</p>
                <p class="text-center text-muted"><a href="#"><strong>Register now</strong></a>! It is easy and done in 1 minute and gives you access to special discounts and much more!</p>
            </div>
        </div>
    </div>
</div>
<!-- *** LOGIN MODAL END ***-->
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
    <div class="container">
        <h2> Esto es un ejemplo</h2>
        <p class="lead">Texto</p>
        <p>mas texto</p>
        <p><a class="btn btn-ghost">Read more</a></p>
    </div>
</section>

<footer class="footer">

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