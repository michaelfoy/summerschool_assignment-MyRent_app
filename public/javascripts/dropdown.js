/*
 * Script to enable semantic dropdown menu
 */
$('.ui.dropdown').dropdown();

$('.ui.fluid.form.segment').form({
  fields: {
    dropdown: {
      identifier: 'id',
      rules: [{
        type: 'empty',
        prompt: 'Please select one of the options',
      },
      ],
    },
  },
});
