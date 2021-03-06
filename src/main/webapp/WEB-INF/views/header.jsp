<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta charset="utf-8"/>
<meta http-equiv="content-type" content="text/html"/>
<meta name="author" content="Maricel Quesada"/>
<meta name="description" content="See your Facebook friends in a map!"/>
<meta name="keywords" content="java, javascript, facebook, friends, maricelquesada"/>

<link rel="icon" href="../../images/favicon.ico" type="image/x-icon"/>

<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="../../stylesheets/main.css"/>
<link href='http://fonts.googleapis.com/css?family=Playball' rel='stylesheet' type='text/css'>

<script type="text/javascript" src="../../js/site.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

<script type="text/javascript"
        src="http://maps.google.com/maps/api/js?key=<c:out value="${mapKey}"/>&sensor=false"></script>
<script type="text/javascript"
        src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox_packed.js"></script>

<script type="text/javascript">var switchTo5x = true;</script>
<script type="text/javascript" src="http://w.sharethis.com/button/buttons.js"></script>
<script type="text/javascript">stLight.options({publisher: "353985c7-fb1e-45b2-a213-394b3d5a3cd3", doNotHash: false, doNotCopy: false, hashAddressBar: false});</script>