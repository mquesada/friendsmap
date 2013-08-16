var addArc = function(map, latOrigin, lngOrigin, latDest, lngDest, color) {
    var connection = new google.maps.Polyline({
        geodesic:true,
        path: [
            new google.maps.LatLng(latOrigin, lngOrigin),
            new google.maps.LatLng(latDest, lngDest)
        ],
        strokeColor: "#" + color,
        strokeOpacity: 1.0,
        strokeWeight: 1
    });
    connection.setMap(map);
};

var addMarker = function(map, lat, lng, title, pic, pinColor) {
    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=star|" + pinColor,
        new google.maps.Size(21, 34),
        new google.maps.Point(0, 0),
        new google.maps.Point(10, 34));
    var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
        new google.maps.Size(40, 37),
        new google.maps.Point(0, 0),
        new google.maps.Point(12, 35));

    var marker = new google.maps.Marker({
        map: map,
        animation: google.maps.Animation.DROP,
        position: new google.maps.LatLng(lat, lng),
        icon: pinImage,
        shadow: pinShadow
    });

    var markerBox = $("<div>", {id: 'marker_box'});
    var pictureBox = $("<div>", {id: 'profile_pic'}).appendTo(markerBox);
    $("<img>", {src: pic}).appendTo(pictureBox);
    $("<div>", {id: 'profile_name'}).html(title).appendTo(markerBox);

    marker.info = new InfoBox({
        content: markerBox.get(0),
        disableAutoPan: false,
        maxWidth: 0,
        zIndex: null,
        closeBoxURL: "",
        infoBoxClearance: new google.maps.Size(1, 1)
    });

    google.maps.event.addListener(marker, 'mouseover', function() {
        marker.info.open(map, this);
    });

    google.maps.event.addListener(marker, 'mouseout', function() {
        marker.info.close();
    });

    google.maps.event.addListener(marker, 'click', function() {
        map.setZoom(4);
        map.setCenter(marker.getPosition());
    });
};

var initYear = function() {
    var currentYear = (new Date).getFullYear();
    $("#year").text(currentYear);
};

var init = function() {
    initYear();
    var myOptions = {
        zoom: 2,
        center: new google.maps.LatLng(0, 0),
        mapTypeId: google.maps.MapTypeId.SATELLITE
    };
    return new google.maps.Map(document.getElementById("map_canvas"), myOptions);
};
