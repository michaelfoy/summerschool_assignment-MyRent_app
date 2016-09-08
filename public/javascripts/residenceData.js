//Passes data for edit/delete residence buttons
$(document).ready(function () {
  $('#edit').click(function () {
    $('#buttonChoice').val('edit');
  });

  $('#delete').click(function () {
    $('#buttonChoice').val('delete');
    $('#warning').addClass('ui visible negative').removeClass('ui hidden');
  });
});

