// Autenticación simplificada para desarrollo: pide un token al backend (/dev/token)
// y lo guarda en el navegador para enviarlo en cada petición GraphQL.

const TOKEN_KEY = 'eventhub_token';
const API_URL = 'http://localhost:8080';

export async function login(user: string, role: 'USER' | 'ADMIN' = 'USER'): Promise<void> {
  const res = await fetch(`${API_URL}/dev/token?user=${encodeURIComponent(user)}&role=${role}`);
  const body = (await res.json()) as { token: string };
  localStorage.setItem(TOKEN_KEY, body.token);
}

export function logout(): void {
  localStorage.removeItem(TOKEN_KEY);
}

export function getToken(): string | null {
  return localStorage.getItem(TOKEN_KEY);
}

/** Lee los roles del payload del JWT (sin verificar la firma: solo para pintar la UI). */
export function getRoles(): string[] {
  const token = getToken();
  if (!token) return [];
  try {
    const payload = JSON.parse(atob(token.split('.')[1])) as { roles?: string[] };
    return payload.roles ?? [];
  } catch {
    return [];
  }
}

export function isAdmin(): boolean {
  return getRoles().includes('ADMIN');
}
