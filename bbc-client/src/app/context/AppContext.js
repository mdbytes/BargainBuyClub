import { createContext, useState } from 'react';
import {
  authenticateUser,
  registerUser,
  logoutUser,
} from '../../api/Authentication';
import { getAlertsByUserId, getAllAlerts, getUserById } from '../../api';
import { useNavigate } from 'react-router-dom';

//1. Create context
export const AppContext = createContext();

//2. Share the context with a provider
export const AppProvider = ({ children }) => {
  //3. Create state for app to manage
  const [authenticatedUser, setAuthenticatedUser] = useState(false);
  const [loginError, setLoginError] = useState('');
  const [signupError, setSignupError] = useState('');
  const [user, setUser] = useState(null);
  const [userToken, setUserToken] = useState('');
  const [userIsAdmin, setUserIsAdmin] = useState(false);
  const [alerts, setAlerts] = useState([]);
  const [systemAlerts, setSystemAlerts] = useState([]);

  const navigate = useNavigate();

  const loginSubmit = async (e) => {
    e.preventDefault();
    let email = e.target[0].value;
    let password = e.target[1].value;

    try {
      let data = await authenticateUser(email, password);
      let res = await setLocalContext(data.userId, email, data.token);
      console.log('logout status: ' + res);
      navigate('/alerts', { replace: true });
    } catch (err) {
      console.log(err);
      setLoginError('Sign in credentials invalid.');
    }
  };

  const setLocalContext = async (userId, email, token) => {
    let user = await getUserById(userId);
    let alerts = await getAlertsByUserId(userId);
    if (user.role === 'ADMIN') {
      setUserIsAdmin(true);
      let currentSystemAlerts = await getAllAlerts();
      setSystemAlerts(currentSystemAlerts);
      localStorage.setItem('bbcAuthUser', true);
    }
    setUser(user);
    setAlerts(alerts);
    setAuthenticatedUser(true);
    setUserToken(token);
    localStorage.setItem('userId', userId);
    localStorage.setItem('email', email);
    localStorage.setItem('bbcToken', token);
  };

  const clearLocalContext = () => {
    setUser(null);
    setAlerts([]);
    setAuthenticatedUser(false);
    setUserToken('');
    setSystemAlerts([]);
    setUserIsAdmin(false);
    setAuthenticatedUser(false);
    setUserToken('');
    localStorage.removeItem('userId');
    localStorage.removeItem('email');
    localStorage.removeItem('bbcToken');
    localStorage.removeItem('bbcAuthUser');
  };

  const signupSubmit = async (e) => {
    e.preventDefault();
    console.dir(e.target);
    let firstName = e.target[0].value;
    let lastName = e.target[1].value;
    let email = e.target[2].value;
    let password = e.target[3].value;

    try {
      let data = registerUser(firstName, lastName, email, password);
      let setup = await setLocalContext(data.userId, email, data.token);
      console.log('context setup status: ' + setup.status);
      navigate('/alerts', { replace: true });
    } catch (err) {
      console.log(err);
      setSignupError('Unable to register new users at this time.');
    }
  };

  const handleLogout = async (e) => {
    e.preventDefault();
    clearLocalContext();
    logoutUser(e);
    navigate('/', { replace: true });
  };

  return (
    <AppContext.Provider
      value={{
        loginSubmit,
        signupSubmit,
        handleLogout,
        alerts,
        setAlerts,
        systemAlerts,
        setSystemAlerts,
        loginError,
        signupError,
        authenticatedUser,
        user,
        setUser,
        userToken,
        setUserToken,
        userIsAdmin,
        setUserIsAdmin,
        setLocalContext,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};
