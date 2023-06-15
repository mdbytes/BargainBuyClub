import React, { useRef, useEffect, useState, useContext } from 'react';
import { useLocation, Link } from 'react-router-dom';
import shoppingCart from '../../assets/images/shopping-cart.png';
import { sendNotifications, updateSystemPrices } from '../../api';
import { AppContext } from '../context/AppContext';
import NotificationModal from '../../components/nav/NotificationModal';
import processingWheel from '../../assets/images/wheel.gif';
import PriceUpdateModal from '../../components/nav/PriceUpdateModal';

export const Navbar = () => {
  let [currentPage, setCurrentPage] = useState('');
  let [pricesUpdated, setPricesUpdated] = useState(0);
  let [notificationsSent, setNotificationsSent] = useState(0);
  let [showProcessingWheel, setShowProcessingWheel] = useState(false);

  const { authenticatedUser, userIsAdmin, handleLogout, setLocalContext } =
    useContext(AppContext);

  let navTogglerRef = useRef(null);

  let location = useLocation();

  let path = location.pathname;

  const updatePrices = async () => {
    setShowProcessingWheel(true);
    let res = await updateSystemPrices();
    setShowProcessingWheel(false);
    setPricesUpdated(res);
  };

  const handleNotifications = async () => {
    setShowProcessingWheel(true);
    let res = await sendNotifications();
    setShowProcessingWheel(false);
    setNotificationsSent(res);
  };

  const onLogout = (e) => {
    setPricesUpdated(0);
    setNotificationsSent(0);
    handleLogout(e);
  };

  let userId = localStorage.getItem('userId');
  let email = localStorage.getItem('email');
  let token = localStorage.getItem('bbcToken');

  let userIsAvailable = userId && email && token;

  if (userIsAvailable && !authenticatedUser) {
    setLocalContext(userId, email, token);
  }

  useEffect(() => {
    setCurrentPage(path);
  }, [path]);

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">
            <img src={shoppingCart} alt="bbc shopping cart" height="35" />
          </Link>
          <button
            ref={navTogglerRef}
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav w-100 mb-2 mb-lg-0 d-flex justify-content-between">
              <div className="site-links">
                <li className="nav-item">
                  {currentPage === '/' ? (
                    <Link className="nav-link active" to="/">
                      Home
                    </Link>
                  ) : (
                    <Link className="nav-link" to="/">
                      Home
                    </Link>
                  )}
                </li>
                <li className="nav-item">
                  {currentPage === '/about' ? (
                    <Link className="nav-link active" to="/about">
                      About
                    </Link>
                  ) : (
                    <Link className="nav-link" to="/about">
                      About
                    </Link>
                  )}
                </li>
                <li className="nav-item">
                  {currentPage === '/contact' ? (
                    <Link className="nav-link active" to="/contact">
                      Contact
                    </Link>
                  ) : (
                    <Link className="nav-link" to="/contact">
                      Contact
                    </Link>
                  )}
                </li>

                {authenticatedUser ? (
                  <li className="nav-item">
                    {currentPage === '/alerts' ? (
                      <Link className="nav-link active" to="/alerts">
                        Alerts
                      </Link>
                    ) : (
                      <Link className="nav-link" to="/alerts">
                        Alerts
                      </Link>
                    )}
                  </li>
                ) : null}

                {userIsAdmin ? (
                  <li className="nav-item dropdown">
                    {currentPage === '/admin/alerts' ||
                    currentPage === '/admin/users' ? (
                      <button
                        className="nav-link dropdown-toggle active bg-dark text-light"
                        data-bs-toggle="dropdown"
                        aria-expanded="false"
                        style={{ border: 'none' }}
                      >
                        Admin
                      </button>
                    ) : (
                      <button
                        className="nav-link dropdown-toggle active bg-dark text-light"
                        data-bs-toggle="dropdown"
                        aria-expanded="false"
                        style={{ border: 'none' }}
                      >
                        Admin
                      </button>
                    )}

                    <ul
                      className="dropdown-menu bg-dark"
                      style={{ border: 'none' }}
                    >
                      <li>
                        <Link className="nav-link active" to="/admin/alerts">
                          Alerts
                        </Link>
                      </li>
                      <li>
                        <Link className="nav-link active" to="/admin/users">
                          Users
                        </Link>
                      </li>
                      <li>
                        <button
                          className="nav-link active bg-dark text-light"
                          style={{ border: 'none' }}
                          onClick={() => updatePrices()}
                        >
                          Update Prices
                        </button>
                      </li>
                      <li>
                        <button
                          className="nav-link active bg-dark text-light"
                          style={{ border: 'none' }}
                          onClick={() => handleNotifications()}
                        >
                          Send Notifications
                        </button>
                      </li>
                    </ul>
                  </li>
                ) : null}
              </div>
              <div className="login-logout-links">
                {authenticatedUser ? (
                  <li className="nav-item">
                    <button
                      className="nav-link active bg-dark text-light"
                      style={{ border: 'none' }}
                      onClick={(e) => onLogout(e)}
                    >
                      Logout
                    </button>
                  </li>
                ) : null}
                {!authenticatedUser ? (
                  <>
                    {currentPage === '/login' ? (
                      <>
                        <li className="nav-item">
                          <Link className="nav-link active" to="/login">
                            Register
                          </Link>
                        </li>
                        <li className="nav-item">
                          <Link className="nav-link active" to="/login">
                            Login
                          </Link>
                        </li>
                      </>
                    ) : (
                      <>
                        <li className="nav-item">
                          <Link className="nav-link" to="/login">
                            Register
                          </Link>
                        </li>
                        <li className="nav-item">
                          <Link className="nav-link" to="/login">
                            Login
                          </Link>
                        </li>
                      </>
                    )}
                  </>
                ) : null}
                <li className="nav-item">
                  {currentPage === '/privacy' ? (
                    <Link className="nav-link active" to="/privacy">
                      Privacy
                    </Link>
                  ) : (
                    <Link className="nav-link" to="/privacy">
                      Privacy
                    </Link>
                  )}
                </li>
              </div>
            </ul>
            <form className="d-flex" role="search"></form>
          </div>
        </div>
      </nav>
      {showProcessingWheel ? (
        <div
          style={{
            position: 'absolute',
            top: 0,
            bottom: 0,
            left: 0,
            right: 0,
            zIndex: 1000,
            backgroundColor: 'rgba(0,0,0,.5)',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
          }}
        >
          <img
            src={processingWheel}
            className="img-fluid"
            alt="processing wheel"
          />
        </div>
      ) : null}
      {notificationsSent > 0 ? (
        <NotificationModal notificationsSent={notificationsSent} />
      ) : null}
      {pricesUpdated > 0 ? (
        <PriceUpdateModal pricesUpdated={pricesUpdated} />
      ) : null}
    </>
  );
};
