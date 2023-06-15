import React from 'react';

import spinningWheel from '../../assets/images/wheel.gif';

export const Loading = () => {
  return (
    <div
      style={{
        width: '100%',
        height: '100%',
        maxHeight: '100vh',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
      }}
    >
      <img src={spinningWheel} alt="working on that..." className="img-fluid" />
    </div>
  );
};
