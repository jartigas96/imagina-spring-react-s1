// Tipos escritos A MANO hace tiempo (antes de introducir graphql-codegen).
// Ejercicio Sesión 1: compáralos con el schema real y sustitúyelos por los tipos generados.

export interface TicketType {
  id: string;
  name: string;
  price: number;
  available: number;
}

export interface EventSummary {
  id: string;
  title: string;
  date: string;
  venueName: string;
  ticketTypes: TicketType[];
}

export type OrderStatus = 'PAID' | 'CANCELLED';

export interface OrderSummary {
  id: string;
  quantity: number;
  total: number;
  status: OrderStatus;
  event: { title: string };
}
