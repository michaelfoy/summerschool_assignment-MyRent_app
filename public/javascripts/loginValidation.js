/*
 * Checks for correct log in details
 */
$('.ui.fluid.form.segment').form({
  fields: {
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
  },
});