import { useEffect, useState } from 'react';

export const CookiesApproval = () => {
  const [cookiesApproved, setCookiesApproved] = useState(false);

  let cookieDisclosure = null;

  cookieDisclosure = document.querySelector('#cookie-disclosure');

  const cookiesApproval = () => {
    if (window !== null && cookieDisclosure) {
      cookieDisclosure.style.display = 'none';
      setCookiesApproved(true);
    }
  };

  useEffect(() => {
    // Track the approval of cookies and privacy settings
    setTimeout(() => {
      if (cookieDisclosure && !cookiesApproved) {
        cookieDisclosure.style.display = 'flex';
      }
    }, 2000);
  }, [cookiesApproved, cookieDisclosure]);

  return (
    <div
      id="cookie-disclosure"
      className="animate__animated animate__zoomInDown"
    >
      <p>
        We use cookies to ensure you have the best browsing experience on our
        website. By using our site, you acknowledge that you have read and
        understood our
        <a href="/privacy" className="cookie-link">
          Privacy Policy
        </a>{' '}
        and
        <a href="/terms" className="cookie-link">
          Universal Terms of Service.
        </a>
      </p>
      {!cookiesApproved ? (
        <button onClick={() => cookiesApproval()}>Proceed to Site</button>
      ) : null}
    </div>
  );
};
