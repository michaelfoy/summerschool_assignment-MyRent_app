const ADMIN_MAP = (function () {
  let latlng = [];
  let markers = [];
  let map;

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
    $.get('/geoLoc', function (data) {
    }).done(function (data) {
      $.each(data, function (index, res) {
        console.log('Residence: ' + res[0] + ', ' + res[1] + ', ' + res[2]);
      });

      latlng = data;
      setMarkers(latlng);
      infoWindows(latlng);
    });
  }

  /*
   * Creates marker for each element in latlng array
   * Sets the boundaries of the map to enclose all markers
   */
  function setMarkers(latlng) {
    const bounds = new google.maps.LatLngBounds();
    for (let i = 0; i < latlng.length; i++) {
      marker = new google.maps.Marker({
        position: getLatLng(latlng[i]),
        map: map,
      });
      markers[i] = marker;
      bounds.extend(marker.position);
    }
    map.fitBounds(bounds);
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
          window.setContent('EIRCODE: ' + latlng[i][1] + ' | Current tenant: ' + latlng[i][2]);
          window.open(map, marker);
        };
      })());
    }
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
  }
  
  google.maps.event.addDomListener(window, 'load', initialize);
  
  return {
    reloadMarkers : reloadMarkers
  }
  
})();
