<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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




<!-- main -->
<div class="main-w3layouts wrapper">
    <h1>Formulaire d'adhésion</h1>
    <div class="main-agileinfo">
        <div class="agileits-top">
            <form action="./formulaire" method="post">
                <c:if test = "${not empty errorEmail || not empty errorPassword || not empty errorSamePassword || not empty errorApprobation}">
                    <div class="warning"><c:out value="${errorEmail} ${errorPassword} ${errorSamePassword} ${errorApprobation}" escapeXml="false"/></div>
                </c:if>
<%--                <div class="warning">${errorEmail} ${errorPassword} ${errorSamePassword} ${errorApprobation}</div>--%>
                <input class="text email" type="text" name="email" placeholder="Email" value="${email}">
                <input class="text" type="text" name="password" placeholder="Password">
                <input class="text w3lpass" type="text" name="passwordBis" placeholder="Confirm Password">
                <div class="wthree-text">
                    <label class="anim">
                        <input type="checkbox" class="checkbox" name="approbation" ${approbation}>
                        <span>J’ai lu et approuvé les conditions générales de ce site</span>
                    </label>
                    <!-- pour les bubules -->
                    <div class="clear"></div>
                </div>
                <input type="submit" value="SIGNUP">
            </form>
        </div>
    </div>
    <!-- copyright -->
    <div class="colorlibcopy-agile">
        <p>© 2019 TP JAVA EE. All rights reserved | Design by Mélida CVL & Antoine VL</a>
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
