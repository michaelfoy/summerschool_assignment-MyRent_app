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
    messageText: {
      identifier: 'messageText',
      rules: [{
        type: 'empty',
        prompt: 'Please leave a message for the MyRent team',
      },
      ],
    },
  },
});
