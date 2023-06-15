import React, { useEffect, useState, useRef } from 'react';
import { getAlertById, getAlertsByUserId } from '../../api';
import { deleteAlert } from '../../api/Alerts';

export const DeleteAlert = ({ userId, alertId, setAlerts, setDeleteAlert }) => {
  const [alert, setAlert] = useState(null);
  const [deleteAlertError, setDeleteAlertError] = useState('');

  const deleteAlertModalRef = useRef(null);
  const closeButtonRef = useRef(null);
  const openModalButton = useRef(null);

  const handleDeleteAlertSubmit = async (e) => {
    e.preventDefault();
    try {
      let res = await deleteAlert(e, alertId);
      if (res.status === 204) {
        let newAlerts = await getAlertsByUserId(userId);
        console.log('newAlerts', newAlerts);
        setAlerts(newAlerts);
        setDeleteAlert(0);
        closeButtonRef.current.click();
      } else {
        setDeleteAlertError(
          "There's a problem deleting that alert.  Contact us for help."
        );
        setTimeout(() => {
          setDeleteAlert(0);
          closeButtonRef.current.click();
        }, 2000);
      }
    } catch (err) {
      console.log(err);
      setDeleteAlertError(
        "There's a problem retrieving alerts.  Try again later."
      );
    }
  };

  const handleCloseModal = (e) => {
    e.preventDefault();
    setDeleteAlert(0);
    deleteAlertModalRef.current.style.display = 'none';
  };

  useEffect(() => {
    const setUpAlert = async () => {
      console.log('retrieving alert');
      let alertData = await getAlertById(alertId);
      console.log(alertData);
      setAlert(alertData);
      document.querySelector('#deleteAlertModalButton').click();
      document.querySelector('#deleteAlertModal').classList.add('show');
    };
    if (alertId !== 0) {
      setUpAlert();
    }
  }, [alertId]);

  console.log('alert', alert);
  return (
    <>
      <button
        id="deleteAlertModalButton"
        type="button"
        className="btn btn-success"
        data-bs-toggle="modal"
        data-bs-target="#deleteAlertModal"
        style={{ width: '6rem', visibility: 'hidden' }}
        ref={openModalButton}
      >
        Delete Alert
      </button>

      <div
        className="modal fade"
        id="deleteAlertModal"
        tabIndex="-1"
        aria-labelledby="deleteAlertModalLabel"
        aria-hidden="true"
        ref={deleteAlertModalRef}
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="deleteAlertModalLabel">
                Delete Alert
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
                onSubmit={(e) => handleDeleteAlertSubmit(e)}
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
                    value={
                      alert && alert.product ? alert.product.productUrl : ''
                    }
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
                    value={alert && alert.alertPrice ? alert.alertPrice : 0}
                    disabled
                    required
                  />
                  <br />
                </div>

                <br />

                <p className="text-center">
                  Are you sure you want to delete this alert?
                </p>
                <div
                  className="form-group"
                  style={{
                    width: '100%',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                  }}
                >
                  <input
                    className="btn btn-danger btn-large btn-success"
                    type="submit"
                    value="Delete Alert"
                  />
                </div>
                <br />
              </form>
              {deleteAlertError === '' ? (
                ''
              ) : (
                <div className="text-danger">{deleteAlertError}</div>
              )}
              <span className="error-message"></span>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
