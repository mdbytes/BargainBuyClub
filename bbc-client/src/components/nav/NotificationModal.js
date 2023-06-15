import React, { useEffect, useRef } from 'react';

const NotificationModal = ({ notificationsSent }) => {
  const openModalRef = useRef(null);

  useEffect(() => {
    if (openModalRef.current) {
      openModalRef.current.click();
    }
  }, [openModalRef]);

  return (
    <>
      <button
        id="openNotificationModelButton"
        ref={openModalRef}
        type="button"
        className="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#notificationModal"
        style={{ display: 'none' }}
      >
        Launch demo modal
      </button>
      <div
        className="modal fade"
        id="notificationModal"
        tabindex="-1"
        aria-labelledby="notificationModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="notificationModalLabel">
                Notifications Processed
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <p>
                Notifications have been processed. Emails were sent to{' '}
                {notificationsSent} user(s).
              </p>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>{' '}
    </>
  );
};

export default NotificationModal;
