<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!-- //Custom Theme files -->
    <!-- web font -->
    <link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
    <!-- //web font -->

    <title>Formulaire d'adhésion</title>
</head>
<body>
<h1>Formulaire d'adhésion</h1>

<!-- main -->
<div class="main-w3layouts wrapper">
    <h1>Creative SignUp Form</h1>
    <div class="main-agileinfo">
        <div class="agileits-top">
            <form action="./formulaire" method="post">
                <input class="text email" type="text" name="email" placeholder="Email" required="">
                <input class="text" type="text" name="password" placeholder="Password" required="">
                <input class="text w3lpass" type="text" name="passwordBis" placeholder="Confirm Password" required="">
                <div class="wthree-text">
                    <label class="anim">
                        <input type="checkbox" class="checkbox" name="approbation">
                        <span>I Agree To The Terms & Conditions</span>
                    </label>
                    <div class="clear"></div>
                </div>
                <input type="submit" value="SIGNUP">
            </form>
        </div>
    </div>
    <!-- copyright -->
    <div class="colorlibcopy-agile">
        <p>© 2018 Colorlib Signup Form. All rights reserved | Design by Mélida CVL & Antoine VL</a>
        </p>
    </div>
    <!-- //copyright -->
    <ul class="colorlib-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>

</body>
</html>
