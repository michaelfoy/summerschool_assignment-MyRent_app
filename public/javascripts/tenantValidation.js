$('#tenantSignUp').form({
  fields: {
    firstName: {
      identifier: 'firstName',
      rules: [{
        type: 'empty',
        prompt: 'Please enter your first name',
      },
      ],
    },
    lastName: {
      identifier: 'lastName',
      rules: [{
        type: 'empty',
        prompt: 'Please enter your last name',
      },
      ],
    },
    email: {
      identifier: 'email',
      rules: [{
        type: 'empty',
        prompt: 'Please enter your email',
      },
      {
        type: 'email',
        prompt: 'Please enter a valid email address',
      },
      ],
    },
    password: {
      identifier: 'password',
      rules: [{
        type: 'empty',
        prompt: 'Please enter your password',
      },
      ],
    },
    checkbox: {
      identifier: 'checkbox',
      rules: [{
        type: 'checked',
        prompt: 'Please check the checkbox',
      },
      ],
    },
  },
});

$('#changeResidence').form({
  fields: {
    residenceId: {
      identifier: 'residenceId',
      rules: [{
        type: 'empty',
        prompt: 'Please select a residence',
      },
      ],
    },
  },
});
