# AGENTS.md — frontend (React 18 · TypeScript · Apollo)

- Las operaciones GraphQL viven en `src/graphql/`. Tras modificarlas: `npm run codegen` y `npm run typecheck`.
- Usa los tipos de `src/generated/graphql.ts`. `src/types/models.ts` es legado: no añadas tipos nuevos ahí.
- Los importes llegan como `String` decimal ("45.00"): formatea con `Intl.NumberFormat`, nunca con `toFixed` sobre string.
- La UI puede ocultar opciones según el rol, pero **la seguridad la decide el backend**.
- Componentes funcionales y hooks; sin `any`.

## Errores frecuentes del agente en este repositorio

- si hay trazabilidad → se debe de indicar en el chat
