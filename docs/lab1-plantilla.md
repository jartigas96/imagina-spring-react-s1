# Laboratorio 1 · Entrega del equipo

- **Integrantes:**
- **Rama:** s1/
- **Portavoz:**

## Parte A · Onboarding con evidencias

### A3 · Verificación de afirmaciones

| #   | Afirmación del agente            | ruta:línea citada       | ✔ / ✘ | Comentario                                                               |
| --- | -------------------------------- | ----------------------- | ----- | ------------------------------------------------------------------------ |
| 1   | Autorización GraphQL débil       | SecurityConfig.java:39  | ✔     |                                                                          |
| 2   | Servicio — `OrderService.create` | OrderService.java:35-48 | ✔     | A demas de lo indicado en el chat, deja traza en el log de la aplicación |
| 3   | Botón Cancelar                   | MyOrdersPage.tsx:39     | ✔     | El boton solo se muestra cuando tiene el order con el status === "PAID"  |

### A4 · Contexto mínimo

- **Piezas elegidas y por qué:**
  1. OrderService / Tiene la logica para cancelar un pedido
  2. OrderGraphQLController / es la llamada de graphQL (POST)
  3. schema.graphql / Es el esquema de Graphql
- **Comparación** (contexto mínimo frente a `backend/` entera: precisión, longitud, consumo de contexto): Ambos han sido precisos, similar, el del backend ha consumido un 1% mas
- **Reto ★★** (_«¿qué parte de tu respuesta tiene menos evidencia?»_):
  - archivos: La parte con menos evidencia es la de “qué archivos cambiaría” y el detalle de *cómo* denegar
  - backend: Dije que añadiría un test GraphQL (caso A cancela pedido de B / A cancela el suyo). Eso es una recomendación razonable

## Parte B · Rules que se aplican

- **Rule creada** (pega su contenido o indica que está en `.cursor/rules/frontend-react.mdc`): Esta en `.cursor/rules/frontend-react.mdc`
- **Evidencia de aplicación en B2:** La regla se ha aplicado y ha dicho como deberia ser el codigo correctamente
  - `Intl.NumberFormat('es-ES', { style: 'currency', currency: 'EUR' })` es lo que piden `frontend-react.mdc` y `frontend/AGENTS.md`.

```
  const eur = new Intl.NumberFormat('es-ES', {
      style: 'currency',
    currency: 'EUR',
    });

    *// dentro del map:*

    <li *key*={[t.id](http://t.id)}>

    {[t.name](http://t.name)}: {eur.format(Number(t.price))} ({t.available} disponibles)

    </li>
```

- **Resultado del experimento B3** (globs `**/*.jsx`): No ha aplicado la regla ya que no hay jsx en el proyecto, pero si que ha leido el [AGENTS.md](http://AGENTS.md)
- **Entrada añadida en AGENTS.md:**
  ```markdown
  - Los importes llegan como `String` decimal ("45.00"): formatea con `Intl.NumberFormat`, nunca con `toFixed` sobre string.
  ```
- **Reto ★★** (solo `description`): Aqui ha aplicado tanto la rule como el agents

## Parte C · Detective de contrato

- **C1:** ¿qué pasó con `npm run typecheck`? ¿Y con `npm run codegen`? Typecheck pasa y codegen falla (Y con el install tuvimos que hacer -force)

| #   | Discrepancia                                                       | ruta:línea                                                                                                                                                                                | Falla en (compilación / petición / runtime) |
| --- | ------------------------------------------------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------- |
| 1   | Campo `venueName` no existe en `Event`                             | Operación: `frontend/src/graphql/operations.ts:11` · Tipo: `frontend/src/types/models.ts:15` · Uso: `frontend/src/pages/EventsPage.tsx:19` · Real: `schema.graphqls:30` (`venue: Venue!`) | Petcion                                     |
| 2   | `TicketType.price`: schema `String!`, código `number` + `.toFixed` | Schema: `schema.graphqls:45` · Tipo: `models.ts:7` · Uso: `EventsPage.tsx:24` · Selección: `operations.ts:15`                                                                             | Runtime                                     |
| 3   | `Order.total`: schema `String!`, código `number` + `.toFixed`      | Schema: `schema.graphqls:55` · Tipo: `models.ts:24` · Uso: `MyOrdersPage.tsx:35` · Selección: `operations.ts:27`                                                                          | Runtime                                     |
| 4   | `OrderStatus` del consumidor omite `REFUNDED`                      | Schema: `schema.graphqls:70-74` · Tipo: `models.ts:19` · Mapa/UI: `MyOrdersPage.tsx:5-8` y `:36`                                                                                          | Runtime                                     |

- **Hipótesis:** ¿por qué `typecheck` pasa y `codegen` no? El typecheck analiza que el codigo de tsx sea correcto y el codegen falla porque el campo venueName no es conocido en la api
- **Reto ★★:** discrepancias que `codegen` no detecta: Todas las que sean de runtime ya que esas solo pasan mientras se ejcuta la aplicaion

## Dudas para la puesta en común

-
