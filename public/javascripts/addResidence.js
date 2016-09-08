const ADD_RESIDENCE = (function () {
  const latlng = new google.maps.LatLng(53.3473860, -6.2590626);

  // Calls functions to set up map and marker
  function initialize() {
    createMap();
    createMarker();
  }

  // Creates map
  function createMap() {
    const mapOptions = {
      center: new google.maps.LatLng(latlng.lat(), latlng.lng()),
      zoom: 10,
      mapTypeId: google.maps.MapTypeId.ROAD,
    };
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
  }

  // Creates marker, makes it responsive and sets it to map
  function createMarker() {
    marker = new google.maps.Marker({
      map: map,
      draggable: true,
      position: latlng,
      title: 'Drag and drop on your property',
    });

    // Marker listener populates hidden fields on dragend
    google.maps.event.addListener(marker, 'dragend', function () {
      const latLng = marker.getPosition();
      const latlong = latLng.lat().toString().substring(0, 10) + ','
          + latLng.lng().toString().substring(0, 10);

      // publish lat long in geolocation control in html page
      $('#geolocation').val(latlong);

      // update the new marker position
      map.setCenter(latLng);
    });

    marker.setMap(map);
  }

  return initialize();
})();

google.maps.event.addDomListener(window, 'load', ADD_RESIDENCE);
