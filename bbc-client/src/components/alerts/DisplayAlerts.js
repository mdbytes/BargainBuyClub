import React, { useState, useContext } from 'react';
import { AddAlert } from './AddAlert';
import { EditAlert } from './EditAlert';
import { DeleteAlert } from './DeleteAlert';
import { toCurrencyString } from '../../app/utils';
import { AppContext } from '../../app/context/AppContext';
import { Loading } from '../../app/layout/Loading';

const DisplayAlerts = () => {
  const [editAlert, setEditAlert] = useState(0);
  const [deleteAlert, setDeleteAlert] = useState(0);

  const { user, alerts, setAlerts } = useContext(AppContext);

  const handleEditAlert = (e) => {
    setEditAlert(e.target.value);
  };

  const handleDeleteAlert = (e) => {
    setDeleteAlert(e.target.value);
  };

  console.log(deleteAlert);

  if (user && alerts) {
    return (
      <main id="display-alerts-page" className="container">
        <h1>Price Alerts</h1>
        <h6 className="text-black">User email: {user.email} </h6>

        <table className="table table-striped" id="display-alerts-table">
          <thead>
            <tr>
              <th>Alert ID</th>
              <th>Product Name</th>
              <th>Current Price</th>
              <th>Alert Price</th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {user && alerts
              ? alerts.map((alert) => (
                  <tr key={alert.alertId}>
                    <td>{alert.alertId}</td>
                    <td>{alert.product.productName}</td>
                    <td>{toCurrencyString(alert.product.recentPrice)}</td>
                    <td>{toCurrencyString(alert.alertPrice)}</td>
                    <td>
                      <button
                        className="btn btn-info mb-2"
                        value={alert.alertId}
                        onClick={(e) => handleEditAlert(e)}
                      >
                        Edit
                      </button>{' '}
                    </td>
                    <td>
                      <button
                        className="btn btn-danger"
                        value={alert.alertId}
                        onClick={(e) => handleDeleteAlert(e)}
                      >
                        Delete
                      </button>{' '}
                    </td>
                  </tr>
                ))
              : ''}
          </tbody>
        </table>

        <AddAlert setAlerts={setAlerts} />

        <EditAlert
          userId={user.id}
          alertId={editAlert}
          setAlerts={setAlerts}
          setEditAlert={setEditAlert}
        />

        <DeleteAlert
          userId={user.id}
          alertId={deleteAlert}
          setAlerts={setAlerts}
          setDeleteAlert={setDeleteAlert}
        />
      </main>
    );
  } else {
    return (
      <div style={{ height: '80vh' }}>
        <Loading />
      </div>
    );
  }
};

export default DisplayAlerts;
