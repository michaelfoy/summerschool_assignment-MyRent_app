$('.ui.fluid.form.segment').form({
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
    address: {
      identifier: 'address',
      rules: [{
        type: 'empty',
        prompt: 'Please enter your address',
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