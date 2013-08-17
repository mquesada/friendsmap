<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Facebook Friends Map</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<header>
    <nav>
        <div id="logo">Friends Map</div>
    </nav>
</header>
<div class="clear">&nbsp;</div>
<section id="main">
    <div id="sigin_info">
        <div id="picture">
            <img src="../../images/Global-Network-Logo.png"/>
        </div>
        <div id="info">
            <p id="name">See your friends on the map!</p>

            <p>Want to know how your social network looks like?</p>

            <p>Log in with your Facebook account to see your friends on a map and how they are connected to each
                other!</p>

            <p>
                <a id="signInLink" href="#">Log In</a>
            </p>
        </div>
    </div>
</section>
<div class="clear">&nbsp;</div>
<footer>
    <jsp:include page="footer.jsp"></jsp:include>
</footer>
<form id="signInForm" action="<c:url value="/signin/facebook" />" method="POST">
    <input type="hidden" name="scope"
           value="user_about_me,user_location,friends_about_me,friends_location,friends_hometown"/>
</form>
<script type="text/javascript">
    $(window).load(function() {
        $("#signInLink").click(function() {
            $("#signInForm").submit();
        });
    });
    initYear();
</script>
</body>
</html>