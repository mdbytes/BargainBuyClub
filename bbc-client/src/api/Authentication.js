import axios from 'axios';

export const authenticateUser = async (email, password) => {
  try {
    const res = await axios.post(
      'https://bbc-server.mdbytes.us/api/v1/auth/authenticate',
      {
        email: email,
        password: password,
      }
    );
    return res.data;
  } catch (err) {
    return null;
  }
};

export const registerUser = async (firstName, lastName, email, password) => {
  try {
    const res = await axios.post(
      'https://bbc-server.mdbytes.us/api/v1/auth/register',
      {
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password,
      }
    );
    console.log('res', res);
    return res.data;
  } catch (err) {
    console.log(err);

    return null;
  }
};

export const logoutUser = async (e) => {
  try {
    let res = await axios.get(
      'https://bbc-server.mdbytes.us/api/v1/auth/logout'
    );
    return res;
  } catch (err) {
    console.log(err);
  }
};
