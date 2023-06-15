import React from 'react';
import { ContactForm } from './ContactForm';

export const Contact = () => {
  return (
    <div className="contact pt-5">
      <section id="contact" className="get-started container-fluid">
        <div className="container">
          <div className="row">
            <div className="col-lg-6">
              <div className="cta-info w-100">
                <h3>Let us know how to help.</h3>
                <p style={{ color: 'black' }}>
                  Whether you're having trouble getting started, or adding an
                  alert, we want to help out.
                </p>
                <ul className="fa-ul">
                  <li>
                    <span className="fa-li">
                      <i className="fa-solid fa-envelope"></i>
                    </span>
                    Send us a message today.
                  </li>
                  <li>
                    <span className="fa-li">
                      <i className="fa-solid fa-flag-checkered"></i>
                    </span>
                    Give us an idea how we can better meet your needs.
                  </li>
                  <li>
                    <span className="fa-li">
                      <i className="fa-solid fa-money-bill-1-wave"></i>
                    </span>
                    Let us know how to make our app better.
                  </li>
                </ul>
                <p style={{ color: 'black' }}>
                  We will contact you within 24 hours.
                </p>
              </div>
            </div>
            <ContactForm />
          </div>
        </div>
      </section>
    </div>
  );
};
