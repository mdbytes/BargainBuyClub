import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from 'react-router-dom';
import About from '../../components/about/About';
import { Contact } from '../../components/contact/Contact';
import { Home } from '../../components/home';
import { Layout } from '../layout/Layout';
import Terms from '../../components/legal/Terms';
import Privacy from '../../components/legal/Privacy';
import NotFound from '../../components/NotFound';
import DisplayAlerts from '../../components/alerts/DisplayAlerts';
import AdminAlerts from '../../components/alerts/AdminAlerts';
import { DisplayUsers } from '../../components/users/DisplayUsers';
import { Login } from '../../components/login/Login';
import { AuthenticatedRoute } from '../context/AuthenticatedRoute';

// Creates the site router with a wrapper element called Layout
// Layout contains the navigation bar, footer, and metadata to
// be added to the head section of the website.
export const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<Layout />} errorElement={<NotFound />}>
      <Route index element={<Home />} />
      <Route path="about" element={<About />} />
      <Route path="contact" element={<Contact />} />
      <Route path="privacy" element={<Privacy />} />
      <Route path="terms" element={<Terms />} />
      <Route
        path="alerts"
        element={
          <AuthenticatedRoute>
            <DisplayAlerts />
          </AuthenticatedRoute>
        }
      />
      <Route
        path="/admin/alerts"
        element={
          <AuthenticatedRoute>
            <AdminAlerts />
          </AuthenticatedRoute>
        }
      />
      <Route
        path="/admin/users"
        element={
          <AuthenticatedRoute>
            <DisplayUsers />
          </AuthenticatedRoute>
        }
      />
      <Route path="login" element={<Login />} />
    </Route>
  )
);
