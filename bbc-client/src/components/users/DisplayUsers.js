import React, { useEffect, useState } from 'react';
import { getAllUsers, editUser } from '../../api/Users';

export const DisplayUsers = () => {
  const [users, setUsers] = useState([]);

  const handlePromotion = async (user) => {
    let editedUser = {};
    editedUser.id = user.id;
    editedUser.firstName = user.firstName;
    editedUser.lastName = user.lastName;
    editedUser.email = user.email;
    editedUser.password = user.password;
    editedUser.role = 'ADMIN';
    let res = await editUser(editedUser);
    console.log('user edited response:', res);
    setUpUsers();
  };

  const handleDemotion = async (user) => {
    let editedUser = {};
    editedUser.id = user.id;
    editedUser.firstName = user.firstName;
    editedUser.lastName = user.lastName;
    editedUser.email = user.email;
    editedUser.password = user.password;
    editedUser.role = 'USER';
    let res = await editUser(editedUser);
    console.log('user edited response:', res);
    setUpUsers();
  };

  const handleDelete = async (user) => {
    let editedUser = {};
    editedUser.id = user.id;
    editedUser.firstName = user.firstName;
    editedUser.lastName = user.lastName;
    editedUser.email = user.email;
    editedUser.password = user.password;
    editedUser.role = 'NONE';
    let res = await editUser(editedUser);
    console.log('user edited response:', res);
    setUpUsers();
  };

  const setUpUsers = async () => {
    let dbUsers = await getAllUsers();
    setUsers(dbUsers);
  };

  useEffect(() => {
    setUpUsers();
  }, []);

  return (
    <main id="display-users-page" className="container">
      <h1>Users</h1>
      <h6 className="text-black">Admin: {localStorage.getItem('userEmail')}</h6>

      <table className="table" id="display-users-table">
        <thead>
          <tr>
            <th>User ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email Address</th>
            <th>Role</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {users !== []
            ? users.map((user) => (
                <tr key={user.id}>
                  <td>{user.id}</td>
                  <td>{user.firstName}</td>
                  <td>{user.lastName}</td>
                  <td>{user.email}</td>
                  <td>{user.role}</td>
                  <td>
                    {user.role === 'USER' ? (
                      <button
                        className="btn btn-primary mb-2 w-100"
                        onClick={() => handlePromotion(user)}
                      >
                        Promote
                      </button>
                    ) : (
                      <button
                        className="btn btn-secondary mb-2 w-100"
                        onClick={() => handleDemotion(user)}
                      >
                        Demote
                      </button>
                    )}
                  </td>
                  <td>
                    <button
                      className="btn btn-danger w-100"
                      onClick={() => handleDelete(user)}
                    >
                      Remove
                    </button>
                  </td>
                </tr>
              ))
            : ''}
        </tbody>
      </table>
    </main>
  );
};
