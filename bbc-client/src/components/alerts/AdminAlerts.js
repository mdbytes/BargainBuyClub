import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllAlerts } from '../../api';
import { AddAlert } from './AddAlert';
import { EditAlert } from './EditAlert';
import { DeleteAlert } from './DeleteAlert';
import { toCurrencyString } from '../../app/utils';

const AdminAlerts = () => {
  const [alerts, setAlerts] = useState([]);
  const [userId, setUserId] = useState(null);
  const [editAlert, setEditAlert] = useState(0);
  const [deleteAlert, setDeleteAlert] = useState(0);

  const navigate = useNavigate();

  useEffect(() => {
    const setUpAlerts = async () => {
      setUserId(localStorage.getItem('userId'));
      let alerts = await getAllAlerts();
      setAlerts(alerts);
    };

    if (!localStorage.getItem('userId')) {
      localStorage.removeItem('token');
      localStorage.removeItem('userEmail');
      localStorage.removeItem('bbcAdmin');
      localStorage.removeItem('userId');
      navigate('/login', { replace: true });
    } else {
      setUpAlerts();
    }
  }, [userId, editAlert, deleteAlert, navigate]);

  const handleEditAlert = (e) => {
    setEditAlert(e.target.value);
  };

  const handleDeleteAlert = (e) => {
    setDeleteAlert(e.target.value);
  };

  return (
    <main id="display-alerts-page" className="container">
      <h1>Price Alerts</h1>
      <h6 className="text-black">
        User email: {localStorage.getItem('userEmail')}{' '}
      </h6>

      <table className="table table-striped" id="display-alerts-table">
        <thead>
          <tr>
            <th>Alert ID</th>
            <th>Product Name</th>
            <th>Current Price</th>
            <th>Alert Price</th>
            <th>User ID</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {alerts
            ? alerts.map((alert) => (
                <tr key={alert.alertId}>
                  <td>{alert.alertId}</td>
                  <td>{alert.product.productName}</td>
                  <td>{toCurrencyString(alert.product.recentPrice)}</td>
                  <td>{toCurrencyString(alert.alertPrice)}</td>
                  <td>{alert.user.id}</td>
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
        alertId={editAlert}
        setAlerts={setAlerts}
        setEditAlert={setEditAlert}
      />

      <DeleteAlert
        alertId={deleteAlert}
        setAlerts={setAlerts}
        setDeleteAlert={setDeleteAlert}
      />
    </main>
  );
};

export default AdminAlerts;
