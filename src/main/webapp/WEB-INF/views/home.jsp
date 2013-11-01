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
                <li>
                    <div id="pic"><a href="http://www.facebook.com/${user.username}" target="_blank">
                        <div id="userPic"></div>
                    </a></div>
                </li>
                <li>
                    <div id="logout"><a href="<c:url value="/signout" />">Log Out</a></div>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="clear">&nbsp;</div>
<section id="main">
    <div id="notlocated">
        <a id="notlocateda" href="#" onclick="javascript: $('#dialog').dialog('open');">Friends Not Located</a>
    </div>
    <div class="clear">&nbsp;</div>
    <div id="map_canvas">
        <div id="loading_outer">
            <div id="loading">
                <img src="../../images/ajax-loader.gif" alt="Loading map"/>

                <p>Please wait while we load your map ...</p>
            </div>
        </div>
    </div>
</section>
<div class="clear">&nbsp;</div>
<footer>
    <jsp:include page="footer.jsp"></jsp:include>
</footer>

<div id="dialog" title="Friends Not Located">
    <table id="friendsNotLocated">
        <tbody>

        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(window).load(function() {
        $("#userPic").css('background-image', 'url(http://graph.facebook.com/<c:out value="${user.id}"/>/picture)');
        $('#dialog').dialog({
            autoOpen: false,
            height: 300,
            width: 500,
            modal: true
        });
        $(".ui-dialog-titlebar").css("background", "#DF7401");
        init();
    });

</script>
</body>
</html>