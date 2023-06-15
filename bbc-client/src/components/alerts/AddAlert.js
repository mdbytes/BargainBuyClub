import React, { useState } from 'react';
import { getAlertsByUserId } from '../../api';
import { addAlert } from '../../api/Alerts';
import { Loading } from '../../app/layout/Loading';

export const AddAlert = ({ setAlerts }) => {
  const [addAlertError, setAddAlertError] = useState('');
  const [alertProcessing, setAlertProcessing] = useState(false);

  const handleAddAlertSubmit = async (e) => {
    e.preventDefault();
    setAlertProcessing(true);
    let responseStatus = await addAlert(e);
    let userId = localStorage.getItem('userId');
    if (responseStatus === 200) {
      let newAlerts = await getAlertsByUserId(userId);
      setAlerts(newAlerts);
      setAlertProcessing(false);
      document.querySelector('#closeModalButton').click();
    } else {
      setAlertProcessing(false);
      setAddAlertError(
        "There's a problem adding that alert.  Contact us for help."
      );
    }
  };

  if (!alertProcessing) {
    return (
      <>
        <button
          type="button"
          className="btn btn-success"
          data-bs-toggle="modal"
          data-bs-target="#addAlertModal"
          style={{ width: '6rem' }}
        >
          Add Alert
        </button>

        <div
          className="modal fade"
          id="addAlertModal"
          tabIndex="-1"
          aria-labelledby="addAlertModalLabel"
          aria-hidden="true"
        >
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h1 className="modal-title fs-5" id="addAlertModalLabel">
                  Add Alert
                </h1>
                <button
                  id="closeModalButton"
                  type="button"
                  className="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div className="modal-body">
                <form
                  id="add-alert-form"
                  onSubmit={(e) => handleAddAlertSubmit(e)}
                >
                  <div className="form-group">
                    <label htmlFor="store-input">Store:</label>
                    <select
                      name="store"
                      className="form-control"
                      id="store-input"
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
                      required
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
                      required
                    />
                    <br />
                  </div>

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
                      value="Add Alert"
                    />
                    <br />
                  </div>
                </form>
                {addAlertError === '' ? (
                  ''
                ) : (
                  <div className="text-danger">{addAlertError}</div>
                )}
                <span className="error-message"></span>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  } else {
    return (
      <>
        <button
          type="button"
          className="btn btn-success"
          data-bs-toggle="modal"
          data-bs-target="#addAlertModal"
          style={{ width: '6rem' }}
        >
          Add Alert
        </button>

        <div
          className="modal fade"
          id="addAlertModal"
          tabIndex="-1"
          aria-labelledby="addAlertModalLabel"
          aria-hidden="true"
        >
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h1 className="modal-title fs-5" id="addAlertModalLabel">
                  Add Alert
                </h1>
                <button
                  id="closeModalButton"
                  type="button"
                  className="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div className="modal-body">
                <Loading />
                {addAlertError === '' ? (
                  ''
                ) : (
                  <div className="text-danger">{addAlertError}</div>
                )}
                <span className="error-message"></span>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }
};
