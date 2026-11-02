import type { ReactElement } from 'react';
import { BrowserRouter, Link, Navigate, Route, Routes } from 'react-router-dom';
import { isAdmin } from './auth';
import { AdminRefundsPage } from './pages/AdminRefundsPage';
import { EventsPage } from './pages/EventsPage';
import { MyOrdersPage } from './pages/MyOrdersPage';

/** Protege la ruta de backoffice: si no eres ADMIN, te devuelve a la home. */
function RequireAdmin({ children }: { children: ReactElement }) {
  return isAdmin() ? children : <Navigate to="/" replace />;
}

export function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Eventos</Link> · <Link to="/mis-pedidos">Mis pedidos</Link>
        {isAdmin() && (
          <>
            {' '}
            · <Link to="/admin/reembolsos">Reembolsos</Link>
          </>
        )}
      </nav>
      <Routes>
        <Route path="/" element={<EventsPage />} />
        <Route path="/mis-pedidos" element={<MyOrdersPage />} />
        <Route
          path="/admin/reembolsos"
          element={
            <RequireAdmin>
              <AdminRefundsPage />
            </RequireAdmin>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}
