import { gql } from '@apollo/client';

// Operaciones GraphQL del frontend. codegen.ts las valida contra el schema del backend.

export const EVENTS_QUERY = gql`
  query Events($city: String) {
    events(city: $city) {
      id
      title
      date
      venueName
      ticketTypes {
        id
        name
        price
        available
      }
    }
  }
`;

export const MY_ORDERS_QUERY = gql`
  query MyOrders {
    myOrders {
      id
      quantity
      total
      status
      event {
        title
      }
    }
  }
`;

export const CANCEL_ORDER_MUTATION = gql`
  mutation CancelOrder($id: ID!) {
    cancelOrder(id: $id) {
      id
      status
    }
  }
`;

export const ADMIN_ORDERS_QUERY = gql`
  query AdminOrders($status: OrderStatus) {
    orders(status: $status) {
      id
      total
      status
      customer {
        name
        email
      }
    }
  }
`;

export const REFUND_ORDER_MUTATION = gql`
  mutation RefundOrder($id: ID!, $reason: String!) {
    refundOrder(id: $id, reason: $reason) {
      id
      status
    }
  }
`;
