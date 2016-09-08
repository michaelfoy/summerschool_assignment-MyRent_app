const REPORT_MAP = (function () {
  let circle;
  let map;
  const center = new google.maps.LatLng(53.3424669, -6.2123707);

  /*
   * Calls the functions to set map and search circle
   */
  function initialize() {
    createMap();
    createCircle();
  }

  /*
   * Creates a map with default centre and size
   */
  function createMap() {
    const mapDiv = document.getElementById('map-canvas-report');
    const mapOptions = {
      center: center,
      zoom: 10,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
    };
    mapDiv.style.height = '500px';
    map = new google.maps.Map(mapDiv, mapOptions);
  }

  /*
   * Creates the search circle
   * Sets it to the map
   * Makes it responsive
   */
  function createCircle() {
    circle = new google.maps.Circle({
      center: center,
      radius: 8000,
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

  return initialize();

})();

google.maps.event.addDomListener(window, 'load', REPORT_MAP);
