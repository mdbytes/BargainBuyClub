import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
import { getAlertById, getAlertsByUserId } from '../../api';

export const EditAlert = ({ userId, alertId, setAlerts, setEditAlert }) => {
  const [alert, setAlert] = useState(null);
  const [alertPrice, setAlertPrice] = useState(0);
  const [editAlertError, setEditAlertError] = useState('');

  const editAlertModalRef = useRef(null);
  const closeButtonRef = useRef(null);
  const openModalButton = useRef(null);

  const handleEditAlertSubmit = async (e) => {
    e.preventDefault();
    let requestUrl = 'http://localhost:8080/api/v1/alerts/' + alertId;
    let res = await axios.put(requestUrl, {
      alertPrice: alertPrice,
    });
    if (res.status === 200) {
      let newAlerts = await getAlertsByUserId(userId);
      setAlerts(newAlerts);
      setEditAlert(0);
      setAlert(null);
      closeButtonRef.current.click();
    } else {
      setEditAlertError(
        "There's a problem editing that alert.  Contact us for help."
      );
      setTimeout(() => {
        setEditAlert(0);
        closeButtonRef.current.click();
      }, 2000);
    }
  };

  const handleCloseModal = (e) => {
    e.preventDefault();
    setEditAlert(0);
    setAlert(null);
    editAlertModalRef.current.style.display = 'none';
  };

  useEffect(() => {
    const setUpAlert = async () => {
      let alertData = await getAlertById(alertId);
      setAlert(alertData);
      setAlertPrice(alertData.alertPrice);
      document.querySelector('#editAlertModalButton').click();
      document.querySelector('#editAlertModal').classList.add('show');
    };

    if (alertId !== 0 && document.querySelector('#editAlertModal')) {
      setUpAlert();
    }
  }, [alertId]);

  return (
    <>
      <button
        type="button"
        className="btn btn-success"
        data-bs-toggle="modal"
        data-bs-target="#editAlertModal"
        style={{ width: '6rem', display: 'none' }}
        id="editAlertModalButton"
        ref={openModalButton}
      >
        Edit Alert
      </button>

      <div
        className="modal fade"
        id="editAlertModal"
        tabIndex="-1"
        aria-labelledby="editAlertModalLabel"
        aria-hidden="true"
        ref={editAlertModalRef}
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="editAlertModalLabel">
                Edit Alert
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
                ref={closeButtonRef}
                onClick={(e) => handleCloseModal(e)}
              ></button>
            </div>
            <div className="modal-body">
              <form
                id="add-alert-form"
                onSubmit={(e) => handleEditAlertSubmit(e)}
              >
                <div className="form-group">
                  <label htmlFor="store-input">Store:</label>
                  <select
                    name="store"
                    className="form-control"
                    id="store-input"
                    disabled
                  >
                    <optgroup label="Select Store">
                      <option value="1">Amazon.com</option>
                    </optgroup>
                  </select>
                </div>

                <div className="form-group">
                  <label htmlFor="product-url">URL for Product:</label>
                  <input
                    type="text"
                    name="product_url"
                    placeholder="http://..."
                    pattern="https?://.+"
                    title="Must be a valid url beginning with http:// or https://"
                    id="product-url"
                    className="form-control"
                    value={alert ? alert.product.productUrl : ''}
                    disabled
                  />
                  <br />
                </div>

                <div className="form-group">
                  <label htmlFor="alert-price-input">Alert Price:</label>
                  <input
                    type="text"
                    name="alert-price"
                    placeholder="0.00"
                    id="alert-price-input"
                    pattern="[0-9]+(\.[0-9][0-9]?)?"
                    title="Must be decimal in form 0.00"
                    className="form-control"
                    value={alertPrice}
                    onChange={(e) => {
                      setAlertPrice(e.target.value);
                    }}
                    required
                  />
                  <br />
                </div>

                <input
                  className="hidden-element"
                  type="text"
                  defalutValue="edit-alert"
                  name="action"
                />
                <br />

                <div
                  className="form-group"
                  style={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                  }}
                >
                  <input
                    className="btn btn-primary btn-large btn-success"
                    type="submit"
                    value="Edit Alert"
                  />
                  <br />
                </div>
              </form>
              {editAlertError === '' ? (
                ''
              ) : (
                <div className="text-danger">{editAlertError}</div>
              )}
              <span className="error-message"></span>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
