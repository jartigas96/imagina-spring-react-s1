import { useQuery } from '@apollo/client';
import { EVENTS_QUERY } from '../graphql/operations';
import type { EventSummary } from '../types/models';

export function EventsPage() {
  const { data, loading, error } = useQuery<{ events: EventSummary[] }>(EVENTS_QUERY);

  if (loading) return <p>Cargando eventos…</p>;
  if (error) return <p role="alert">Error: {error.message}</p>;

  return (
    <section>
      <h1>Próximos eventos</h1>
      <ul>
        {data?.events.map((event) => (
          <li key={event.id}>
            <h2>{event.title}</h2>
            <p>
              {new Date(event.date).toLocaleString('es-ES')} · {event.venueName}
            </p>
            <ul>
              {event.ticketTypes.map((t) => (
                <li key={t.id}>
                  {t.name}: {t.price.toFixed(2)} € ({t.available} disponibles)
                </li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
    </section>
  );
}
