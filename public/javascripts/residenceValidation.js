/*
 * Script to enable semantic dropdown menu
 */
$('.ui.dropdown').dropdown();

$('#editResidence').form({
  fields: {
    rent: {
      identifier: 'rent',
      rules: [{
        type: 'empty',
        prompt: 'Please enter new rent rate',
      },
      ],
    },
  },
});

$('#newResidence').form({
  fields: {
    eircode: {
      identifier: 'eircode',
      rules: [{
        type: 'empty',
        prompt: 'Please enter the residence eircode',
      },],
    },
    rent: {
      identifier: 'rent',
      rules: [{
        type: 'empty',
        prompt: 'Please enter new rent rate',
      },
      {
        type: 'integer',
        prompt: 'Please enter an integer for monthly rent'
      },
      ],
    },
    bedrooms: {
      identifier: 'bedrooms',
      rules: [{
        type: 'empty',
        prompt: 'Please enter the number of bedrooms',
      },
      {
        type: 'integer',
        prompt: 'Please enter an integer for the number of bedrooms'
      },
      ],
    },
    numberBathrooms: {
      identifier: 'numberBathrooms',
      rules: [{
        type: 'empty',
        prompt: 'Please enter the number of bathrooms',
      },
      {
        type: 'integer',
        prompt: 'Please enter an integer for the number of bathrooms'
      },
      ],
    },
    area: {
      identifier: 'area',
      rules: [{
        type: 'empty',
        prompt: 'Please enter the area of the residence',
      },
      {
        type: 'integer',
        prompt: 'Please enter an integer for residence area in metres squared'
      },
      ],
    },
    residenceType: {
      identifier: 'residenceType',
      rules: [{
        type: 'empty',
        prompt: 'Please select the relevant type of residence',
      },
      ],
    },
  },
});
