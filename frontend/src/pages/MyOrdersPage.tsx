import { useMutation, useQuery } from '@apollo/client';
import { CANCEL_ORDER_MUTATION, MY_ORDERS_QUERY } from '../graphql/operations';
import type { OrderSummary } from '../types/models';

const STATUS_LABEL: Record<OrderSummary['status'], string> = {
  PAID: 'Pagado',
  CANCELLED: 'Cancelado',
};

export function MyOrdersPage() {
  const { data, loading, error } = useQuery<{ myOrders: OrderSummary[] }>(MY_ORDERS_QUERY);
  const [cancelOrder] = useMutation(CANCEL_ORDER_MUTATION, { refetchQueries: [MY_ORDERS_QUERY] });

  if (loading) return <p>Cargando pedidos…</p>;
  if (error) return <p role="alert">Error: {error.message}</p>;

  return (
    <section>
      <h1>Mis pedidos</h1>
      <table>
        <thead>
          <tr>
            <th>Evento</th>
            <th>Entradas</th>
            <th>Total</th>
            <th>Estado</th>
            <th />
          </tr>
        </thead>
        <tbody>
          {data?.myOrders.map((order) => (
            <tr key={order.id}>
              <td>{order.event.title}</td>
              <td>{order.quantity}</td>
              <td>{order.total.toFixed(2)} €</td>
              <td>{STATUS_LABEL[order.status]}</td>
              <td>
                {order.status === 'PAID' && (
                  <button onClick={() => cancelOrder({ variables: { id: order.id } })}>Cancelar</button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
}
