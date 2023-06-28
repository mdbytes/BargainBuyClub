import React, { useEffect, useRef } from 'react';

const PriceUpdateModal = ({ pricesUpdated }) => {
  const openPriceModalRef = useRef(null);

  useEffect(() => {
    if (openPriceModalRef.current) {
      openPriceModalRef.current.click();
    }
  }, [openPriceModalRef]);

  return (
    <>
      <button
        ref={openPriceModalRef}
        type="button"
        className="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#priceUpdateModal"
        style={{ display: 'none' }}
      >
        Launch demo modal
      </button>
      <div
        className="modal fade"
        id="priceUpdateModal"
        tabindex="-1"
        aria-labelledby="priceUpdateModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="priceUpdateModalLabel">
                Prices Updated
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <p>Prices were updated for {pricesUpdated} products.</p>
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

export default PriceUpdateModal;
