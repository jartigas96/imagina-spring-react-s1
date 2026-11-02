import { useState } from 'react';
import { useMutation, useQuery } from '@apollo/client';
import { ADMIN_ORDERS_QUERY, REFUND_ORDER_MUTATION } from '../graphql/operations';

interface AdminOrder {
  id: string;
  total: string;
  status: string;
  customer: { name: string; email: string };
}

/** Backoffice de reembolsos. Solo visible para administradores (ver RequireAdmin en App.tsx). */
export function AdminRefundsPage() {
  const [reason, setReason] = useState('Evento cancelado');
  const { data, loading } = useQuery<{ orders: AdminOrder[] }>(ADMIN_ORDERS_QUERY, {
    variables: { status: 'PAID' },
  });
  const [refundOrder] = useMutation(REFUND_ORDER_MUTATION, { refetchQueries: [ADMIN_ORDERS_QUERY] });

  if (loading) return <p>Cargando…</p>;

  return (
    <section>
      <h1>Reembolsos</h1>
      <label>
        Motivo <input value={reason} onChange={(e) => setReason(e.target.value)} />
      </label>
      <ul>
        {data?.orders.map((o) => (
          <li key={o.id}>
            {o.id} · {o.customer.name} ({o.customer.email}) · {o.total} €{' '}
            <button onClick={() => refundOrder({ variables: { id: o.id, reason } })}>Reembolsar</button>
          </li>
        ))}
      </ul>
    </section>
  );
}
