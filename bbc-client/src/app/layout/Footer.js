import { Link } from 'react-router-dom';

import logo from '../../assets/images/react.svg';
import spring from '../../assets/images/spring.png';

export const Footer = () => {
  return (
    <div className="container-fluid bg-dark text-white">
      <footer className="row border-top py-5">
        <div className="col-md-4">
          <p>
            A full-stack web application utilizing Spring Framework for REST API
            support with REACT Framework for user interface.{' '}
            <Link to="/contact">Contact us</Link> today to start discussing your
            own application needs.
          </p>
        </div>
        <div className="col-md-4 copyright text-center">
          <p>
            &copy;2023{' '}
            <a href="https://mdbytes.com">
              <span className="logo-font">md</span> Web Technologies
            </a>
          </p>
        </div>

        <div className="col-md-4 site-logo-holder">
          <img
            src={spring}
            alt="site logo in footer"
            height="50"
            className="site-logo"
          />
          <img
            src={logo}
            alt="site logo in footer"
            height="50"
            className="site-logo"
          />
        </div>
      </footer>
    </div>
  );
};
