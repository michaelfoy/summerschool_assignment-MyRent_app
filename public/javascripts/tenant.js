/*
 * Formats dropdown elements with semantic ui
 */
$('.ui.dropdown').dropdown();

/*
 * Validates chosen tenant for delete tenant
 */
$('#deleteTenancy').form({
  fields: {
    eircode: {
      identifier: 'eircode',
      rules: [{
        type: 'empty',
        prompt: 'No currently registered tenancy',
      },
      ],
    },
  },
/*  onSuccess : function(event, fields) {
    deleteTenancy();      
    event.preventDefault();
  },*/
});

$('#changeResidence').form({
  fields: {
    dropdown: {
      identifier: 'id',
      rules: [{
        type: 'empty',
        prompt: 'Please select a residence',
      },
      ],
    },
  },
});

 
/*function deleteTenancy() {
  let formData = $('#deleteTenancy').serialize();
  $.ajax({
    type : 'POST',
    url: '/deleteTenancy',
    data: {'formData': formData},
    dataType : 'json',
    success : function(response) {
      console.log("Response: " + response)
      
      // response format: latlng, eircode, rent, id
      $('#notificationRemove').html('TENANCY ENDED');
      $('#eircode').val("");
      addResidenceDropdown(response);
      VACANT_MAP.reloadMarkers(response);
    }
  });
}


 //Removes a specified tenant from the dropdown list
 
 
function addResidenceDropdown(data) {
  $dropdown = $('#dropdownfield');
  $(".ui.search.dropdown").remove();
  $select = $('<select />', {name: 'id'}).addClass('ui search dropdown')
  $select.appendTo($dropdown);
  $('<option />', {value: "", text: 'Residence by EIRCODE',}).appendTo($select);
  
  for (let i = 0; i < data.length; i++) {
    $value = data[i][3];
    $('<option />', {value: $value, text: data[i][1],}).appendTo($select);
        //let $value = data[i][3];
        //let $text = data[i][1]; 
    }
}*/