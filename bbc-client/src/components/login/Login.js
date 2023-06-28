import React, { useContext } from 'react';
import { AppContext } from '../../app/context/AppContext';

import megaPhone from '../../assets/images/megaphone.png';

export const Login = () => {
  const { loginSubmit, signupSubmit, loginError, signupError, setSignupError } =
    useContext(AppContext);

  const handleDevSignUp = (e) => {
    e.preventDefault();
    setSignupError(
      'Registration disabled. Contact us for an online demonstration. '
    );
  };

  return (
    <main id="login-page" className="container ">
      <div className="row">
        <div id="shopper-home">
          <img className="center" src={megaPhone} alt="mega phone" />
          <hr />
        </div>

        <form method="post" id="signin" onSubmit={(e) => loginSubmit(e)}>
          <div className="form-group">
            <h3>Sign In</h3>
            {loginError === '' ? (
              ''
            ) : (
              <div
                className="text-danger text-center mb-3"
                style={{
                  backgroundColor: '#71cfeb',
                  fontWeight: '700',
                  fontStyle: 'italic',
                  padding: '1rem',
                  borderRadius: '.8rem',
                }}
              >
                {loginError}
              </div>
            )}
          </div>

          <div className="form-group sign-in-input mb-3">
            <label htmlFor="sign-in-username">
              <h5>Email Address:</h5>
            </label>
            <input
              type="email"
              className="form-control"
              id="sign-in-username"
              placeholder="Email Address"
              name="email"
              required
            />
          </div>

          <div className="form-group sign-in-input mb-3">
            <label htmlFor="sign-in-password">
              <h5>Password:</h5>
            </label>
            <input
              type="password"
              className="form-control"
              id="sign-in-password"
              placeholder="Password"
              name="password"
              pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$"
              title="Must be 6 characters long, contain one upper case letter, one lower case letter, and one numeric digit"
              required
            />
          </div>

          <button type="submit" className="btn btn-primary btn-success">
            Sign In
          </button>
          <span className="error-message"></span>
        </form>
      </div>
      <hr />

      <div className="row">
        {/* <form method="get" id="signup" onSubmit={(e) => signupSubmit(e)}> */}
        <form method="get" id="signup">
          <div className="form-group">
            <h3>Or Sign Up</h3>
          </div>
          {signupError === '' ? (
            ''
          ) : (
            <div
              className="text-danger text-center mb-3"
              style={{
                backgroundColor: '#71cfeb',
                fontWeight: '700',
                fontStyle: 'italic',
                padding: '1rem',
                borderRadius: '.8rem',
              }}
            >
              {signupError}
            </div>
          )}

          <div className="form-row">
            <div className="form-group sign-up-input mb-2">
              <label htmlFor="first-name">
                <h5>First name:</h5>
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="First name"
                name="first-name"
                id="first-name"
                required
              />
            </div>
            <div className="form-group sign-up-input mb-2">
              <label htmlFor="last-name">
                <h5>Last name:</h5>
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Last name"
                name="last-name"
                id="last-name"
                required
              />
            </div>
          </div>
          <div className="form-row">
            <div className="form-group sign-up-input mb-3">
              <label htmlFor="username">
                <h5>Email address:</h5>
              </label>
              <input
                type="email"
                className="form-control"
                id="sign-up-username"
                placeholder="Email (Username)"
                name="username"
                required
              />
            </div>
            <div className="form-group sign-up-input mb-3">
              <label htmlFor="sign-up-password">
                <h5>Password:</h5>
              </label>
              <input
                type="password"
                name="password"
                className="form-control"
                id="sign-up-password"
                placeholder="Password"
                pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$"
                title="Must be 6 characters long, contain one upper case letter, one lower case letter, and one numeric digit"
                required
              />
            </div>
          </div>
          <input type="text" name="action" className="hidden-element" />
          <button
            type="submit"
            className="btn btn-primary btn-success"
            onClick={(e) => handleDevSignUp(e)}
          >
            Sign Up
          </button>

          <p style={{ clear: 'both', paddingTop: '2rem' }}>
            Password must be at least 6 characters and must include at least one
            upper case letter, one lower case letter, and one numeric digit.
          </p>
        </form>
      </div>
    </main>
  );
};
