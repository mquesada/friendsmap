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
        <div id="menu">
            <ul>
                <li><div id="pic"><a href="http://www.facebook.com/${user.facebookProfile.username}" target="_blank"><div id="userPic"></div></a></div></li>
                <li><div id="logout"><a href="<c:url value="/signout" />">Log Out</a></div></li>
            </ul>
        </div>
    </nav>
</header>
<div class="clear">&nbsp;</div>
<section id="main">
    <div id="map_canvas"></div>
</section>
<div class="clear">&nbsp;</div>
<footer>
    <jsp:include page="footer.jsp"></jsp:include>
</footer>
<script type="text/javascript">
    function initPage() {
        $("#userPic").css('background-image', 'url(http://graph.facebook.com/<c:out value="${user.facebookProfile.id}"/>/picture)');
        var map = init();
    <c:forEach items="${friends}" var="friend">
    <c:if test="${not empty friend.location}">
        addMarker(map, <c:out value="${friend.location.latitude}"/>, <c:out value="${friend.location.longitude}"/>, "<c:out value="${friend.facebookProfile.name}"/>", "<c:out value="${friend.pictureUrl}"/>", "<c:out value="${friend.location.countryColorCode}"/>");
    </c:if>
    </c:forEach>
    <c:forEach items="${friends}" var="friend">
    <c:if test="${not empty friend.location}">
    <c:forEach items="${friend.friends}" var="mutualFriend">
    <c:if test="${not empty mutualFriend.location}">
        addArc(map, <c:out value="${friend.location.latitude}"/>, <c:out value="${friend.location.longitude}"/>, <c:out value="${mutualFriend.location.latitude}"/>, <c:out value="${mutualFriend.location.longitude}"/>, "<c:out value="${friend.location.countryColorCode}"/>");
    </c:if>
    </c:forEach>
    </c:if>
    </c:forEach>
    }

    $(window).load(function() {
        initPage();
    });

</script>
</body>
</html>