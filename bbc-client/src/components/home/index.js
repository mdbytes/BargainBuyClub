import React, { useEffect, useState } from 'react';
import shoppingCart from '../../assets/images/shopping-cart.png';
import megaPhone from '../../assets/images/megaphone.png';
import { getHomeAlerts } from '../../api/Alerts';
import { toCurrencyString } from '../../app/utils';

export const Home = () => {
  const [homeAlerts, setHomeAlerts] = useState([]);

  useEffect(() => {
    const setUpAlerts = async () => {
      let alertData = await getHomeAlerts();
      setHomeAlerts(alertData);
    };

    setUpAlerts();
  }, []);

  return (
    <div className="container-fluid home">
      <div className="row">
        <div className="col-lg-6" id="home-left-column">
          <div id="home-jumbo" className="jumbotron text-center">
            <h1>BargainBuyClub.com</h1>
            <img src={megaPhone} id="home-page-megaphone" alt="mega phone" />

            <h5>Filling your cart at the right price</h5>
            <hr />
          </div>

          <div className="center" id="get-started">
            <a href="/login" className="btn btn-large btn-success">
              Get Started
            </a>
          </div>

          <div id="mobile-hr-addition">
            <hr />
          </div>
        </div>

        <div className="col-lg-6" id="home-right-column">
          <div className="row">
            <div id="shopper-home">
              <img src={shoppingCart} alt="shopping cart" />
              <hr />
            </div>
            <h5>We let you know when the price is right!</h5>
          </div>
          <p>
            Tell us the product and the price you want to pay. You can come back
            to the site to check your alerts. As an example, here are some items
            we are tracking from the Amazon store online.
          </p>
          <h3>Featured Price Alerts</h3>

          <table className="table table-striped" id="home_alerts">
            <thead>
              <tr>
                <th>Product Name</th>
                <th>Store</th>
                <th>Alert Price</th>
                <th>Recent Price</th>
              </tr>
            </thead>
            <tbody>
              {homeAlerts !== []
                ? homeAlerts.map((alert, index) => {
                    return (
                      <tr key={index}>
                        <td>{alert.product.productName}</td>
                        <td>
                          <a href={alert.product.productUrl}>
                            {alert.store.storeName}
                          </a>
                        </td>
                        <td>{toCurrencyString(alert.alertPrice)}</td>
                        <td>{toCurrencyString(alert.product.recentPrice)}</td>
                      </tr>
                    );
                  })
                : ''}
            </tbody>
          </table>
          <p>
            When the online store price reaches your target, an email alert will
            automatically be sent to you.
          </p>
        </div>
      </div>
    </div>
  );
};
