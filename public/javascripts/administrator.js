/*
 * Formats dropdown elements with semantic ui
 */
$('.ui.dropdown').dropdown();

/*
 * Validates chosen tenant for delete tenant
 */
$('#deleteTenant').form({
  fields: {
    dropdown: {
      identifier: 'tenant',
      rules: [{
        type: 'empty',
        prompt: 'Please select a tenant to delete',
      },
      ],
    },
  },
  onSuccess : function(event, fields) {
    ajaxTenant();      
    event.preventDefault();
   },
});

/*
 * Validates chosen landlord for delete landlord
 */
$('#deleteLandlord').form({
  fields: {
    dropdown: {
      identifier: 'landlord',
      rules: [{
        type: 'empty',
        prompt: 'Please select landlord to delete',
      },
      ],
    },
  },
  onSuccess : function(event, fields) {
    ajaxLandlord();      
    event.preventDefault();
   },
});

/*
 * Sends AJAX request to delete tenant, on success: updates map markers and dropdown
 */
function ajaxTenant() {
  let formData = $('#deleteTenant').serialize();
  $.ajax({
    type : 'POST',
    url: '/deleteTenant',
    data: {'formData': formData},
    dataType : 'json',
    success : function(response) {
      
      // response format: tenantId, latlng, eircode, marker message
      $('#notificationTenant').html('TENANT DELETED');
      removeTenantDropdown(response[0][0]);
      response.shift();
      ADMIN_MAP.reloadMarkers(response);
    }
  });
}

/*
 * Removes a specified tenant from the dropdown list
 * 
 */
function removeTenantDropdown(tenantId) {
  let $tenants = $('.tenant.input');
  for (let i = 0; i < $tenants.length; i += 1) {
    if($tenants[i].getAttribute('value').localeCompare(tenantId) == 0) {
      $tenants[i].remove();
      $('#tenantDropdown').dropdown('clear');
      break;
    }
  }
}

/*
 * Sends AJAX request to delete landlord, on success: updates map markers and dropdown
 */
function ajaxLandlord() {
  let formData = $('#deleteLandlord').serialize();
  console.log(formData);
  $.ajax({
    type : 'POST',
    url: '/deleteLandlord',
    data: {'formData': formData},
    dataType : 'json',
    success : function(response) {
      
      // response format: landlordId, latlng, eircode, marker message
      $('#notificationLandlord').html('LANDLORD DELETED');
      removeLandlordDropdown(response[0][0]);
      response.shift();
      ADMIN_MAP.reloadMarkers(response);
    }
  });
}

/*
 * Removes a specified landlord from the dropdown list
 * 
 */
function removeLandlordDropdown(landlordId) {
  let $landlords = $('.landlord.input');
  for (let i = 0; i < $landlords.length; i += 1) {
    if($landlords[i].getAttribute('value').localeCompare(landlordId) == 0) {
      $landlords[i].remove();
      $('#landlordDropdown').dropdown('clear');
      break;
    }
  }
}
