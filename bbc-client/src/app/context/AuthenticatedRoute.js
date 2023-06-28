import React, { useContext, useEffect } from 'react';
import { AppContext } from './AppContext';
import { Navigate } from 'react-router-dom';

export const AuthenticatedRoute = ({ children }) => {
  const { authenticatedUser, setLocalContext } = useContext(AppContext);

  let userId = localStorage.getItem('userId');
  let email = localStorage.getItem('email');
  let token = localStorage.getItem('bbcToken');

  let userIsAvailable = userId && email && token;

  useEffect(() => {}, []);

  if (authenticatedUser) {
    return children;
  } else if (userIsAvailable) {
    setLocalContext(userId, email, token);
    return children;
  } else {
    return <Navigate to="/login" />;
  }
};
