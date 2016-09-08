const VACANT_MAP = (function () {
  let latlng = [];
  let markers = [];
  let map;
  let center;
  let radius;
  const bounds = new google.maps.LatLngBounds();

  // Calls functions to set up map, markers and info windows
  function initialize() {
    newMap();
    getData();
  }

  /*
   * Creates a map of default height
   */
  function newMap() {
    const mapOptions = {
      mapTypeId: google.maps.MapTypeId.ROADMAP,
    };
    const mapDiv = document.getElementById('map-canvas-report');
    mapDiv.style.height = '500px';
    map = new google.maps.Map(mapDiv, mapOptions);
  }

  /*
   * Retrieves residence data from database
   * Stores data in latlng array
   * Calls setMarkers() & infoWindows
   */
  function getData() {
    $.get('/vacRes', function (data) {
    }).done(function (data) {
      $.each(data, function (index, res) {
        console.log('Residence: ' + res[0] + ', ' + res[1] + ', ' + res[2]);
      });
      latlng = data;
      console.log(data);
      setMarkers(latlng);
      infoWindows(latlng);
    });
  }

  /*
   * Creates marker for each element in latlng array
   * Sets the boundaries of the map to enclose all markers
   */
  function setMarkers(latlng) {
    for (let i = 0; i < latlng.length; i++) {
      marker = new google.maps.Marker({
        position: getLatLng(latlng[i]),
        map: map,
      });
      markers[i] = marker;
      bounds.extend(marker.position);
    }
    map.fitBounds(bounds);
    radialMultiplier(bounds.getNorthEast(), bounds.getSouthWest());
    center = bounds.getCenter();
    createCircle();
  }

  /*
   * Gets coordinates from the residence data,
   * then creates LatLng object from coordinates
   */
  function getLatLng(data) {
    const coordinates = data[0].split(',');
    return new google.maps.LatLng(coordinates[0], coordinates[1]);
  }
  
  /*
  * Sets radius of search circle relative to map size
  */ 
  function radialMultiplier(latLngA, latLngB) {
    const dist = google.maps.geometry.spherical.computeDistanceBetween (bounds.getNorthEast(), bounds.getSouthWest());
    radius = (dist / 4);
  }
  
  /*
   * Creates an info window for each marker
   * Only one window can be opened at a time
   */
  function infoWindows(latlng) {
    const window = new google.maps.InfoWindow();
    for (let i = 0; i < latlng.length; i++) {
      const marker = markers[i];

      // IIFE as parameter ensures that for each click the function is executed
      google.maps.event.addListener(marker, 'click', (function () {
        
        // Closure provides separate execution context for each marker-window pair
        return function () {
          window.setContent('EIRCODE: ' + latlng[i][1] + ' | Rent: €' + latlng[i][2]);
          window.open(map, marker);
        };
      })());
    }
  }
  
  /*
   * Creates the search circle
   * Sets it to the map
   * Makes it responsive
   */
  function createCircle() {
    circle = new google.maps.Circle({
      center: center,
      radius: radius,
      strokeColor: '#0000FF',
      strokeOpacity: 0.4,
      strokeWeight: 1,
      fillColor: '#0000FF',
      fillOpacity: 0.4,
      draggable: true,
    });
    circle.setEditable(true);
    circleData();
    circle.setMap(map);
    circleListener();
  }

  // Relates circle data to html page
  function circleData() {
    const center = circle.getCenter();
    const latcenter = center.lat().toString();
    const lngcenter = center.lng().toString();
    const radius = circle.getRadius().toString();
    $('#radius').val(radius);
    $('#lat').val(latcenter);
    $('#lng').val(lngcenter);
  }

  // Circle listener - updates circle data according to user action
  function circleListener() {
    google.maps.event.addListener(circle, 'center_changed', function () {
      circleData();
    });

    google.maps.event.addListener(circle, 'radius_changed', function () {
      circleData();
    });
  }
  
  /*
   * Removes then reloads markers, used for AJAX calls
   */
  function reloadMarkers(data) {
    removeMarkers();
    setMarkers(data);
    infoWindows(data);
  }
  
  /*
   * Removes all markers from the map
   */
  function removeMarkers() {
    for (i = 0; i < markers.length; i++)  {
      markers[i].setMap(null);
    }
    circle.setMap(null);
  }
  
  google.maps.event.addDomListener(window, 'load', initialize);
  
  return {
    reloadMarkers : reloadMarkers
  }
  
})();
