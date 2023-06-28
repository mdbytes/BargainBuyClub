import axios from 'axios';

export const createNewUser = async (user) => {
  let res = await axios.post(
    `https://bbc-server.mdbytes.us/api/vi/users`,
    user
  );
  console.log('status', res.status);
  return res.status;
};

export const getAllUsers = async () => {
  let res = await axios.get('https://bbc-server.mdbytes.us/api/v1/users');
  let allUsers = res.data._embedded.users;
  let activeUsers = allUsers.filter((user) => user.role !== 'NONE');
  return activeUsers;
};

export const getUserById = async (id) => {
  let res = await axios.get(`https://bbc-server.mdbytes.us/api/v1/users/${id}`);
  return res.data;
};

export const getUserByEmail = async (email) => {
  let res = await axios.get(
    `https://bbc-server.mdbytes.us/api/v1/admin/users?email=${email}`
  );
  return res.data;
};

export const editUser = async (user) => {
  let url = `https://bbc-server.mdbytes.us/api/v1/users/${user.id}`;
  let res = await axios.put(url, user);
  return res;
};

export const deleteUser = async (user) => {
  let url = `https://bbc-server.mdbytes.us/api/v1/admin/users/delete/${user.id}`;
  let res = await axios.delete(url);
  return res;
};
