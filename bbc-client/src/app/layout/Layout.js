import { Fragment } from 'react';
import { Footer } from './Footer';
import { Navbar } from './Navbar';
import { CookiesApproval } from '../../components/nav/CookiesApproval';
import { BackToTop } from '../../components/nav/BackToTop';
import { Outlet } from 'react-router-dom';
import { ScrollToTop } from '../../components/nav/ScrollToTop';
import { AppProvider } from '../context/AppContext';

export const Layout = () => {
  return (
    <AppProvider>
      <Fragment>
        <ScrollToTop />
        <Navbar />
        <Outlet />
        <Footer />
        <BackToTop />
        <CookiesApproval />
      </Fragment>
    </AppProvider>
  );
};
