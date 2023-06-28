import { useEffect } from 'react';
import { Footer } from '../app/layout/Footer';
import { Navbar } from '../app/layout/Navbar';

/**
 * The NotFound function takes no parameters and returns a JSX element
 * indicating that the requested page was not found.  This element is identified
 * as the 'errorElement' in the react router component.
 *
 * @returns JSX Element
 */
export const NotFound = () => {
  // Redirects users to home page 4 seconds after page loads
  useEffect(() => {
    // setTimeout(() => {
    //   window.location.replace('/');
    // }, 4000);
  }, []);

  return (
    <>
      <Navbar />
      <div className="not-found">
        <h1>OOPS! </h1>
        <h2>Page Not Found</h2>

        <p>We couldn't find that page on our site.</p>

        <p>Redirecting to Home Page</p>
        <p>
          Click <a href="/">here</a> to go there now.
        </p>
      </div>
      <Footer />
    </>
  );
};

export default NotFound;
