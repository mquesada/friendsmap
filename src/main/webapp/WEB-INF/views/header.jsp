<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta charset="utf-8"/>
<meta http-equiv="content-type" content="text/html"/>
<meta name="author" content="Maricel Quesada"/>
<meta name="description" content="See your Facebook friends in a map!"/>
<meta name="keywords" content="java, javascript, facebook, friends, maricelquesada"/>


<link rel="icon" href="../../images/favicon.ico" type="image/x-icon"/>

<link rel="stylesheet" type="text/css" href="../../stylesheets/main.css"/>
<link href='http://fonts.googleapis.com/css?family=Playball' rel='stylesheet' type='text/css'>

<script type="text/javascript" src="../../js/site.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript"
        src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.24/jquery-ui.min.js"></script>
<script type="text/javascript"
        src="http://maps.google.com/maps/api/js?key=<c:out value="${mapKey}"/>&sensor=false"></script>
<script type="text/javascript"
        src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox_packed.js"></script>

<script type="text/javascript">var switchTo5x = false;</script>
<script type="text/javascript" src="http://w.sharethis.com/button/buttons.js"></script>
<script type="text/javascript">stLight.options({publisher:'b8af712d-fde1-45f8-948b-6e5b740cc0d5'});</script>