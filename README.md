# EventHub · repositorio de prácticas

Curso **Cursor para Desarrolladores FullStack de Spring y React** — Imagina Formación · Joan Cifo

> ⚠️ Proyecto de formación con **errores y vulnerabilidades sembrados a propósito**.
> No lo despliegues ni reutilices su código de seguridad.

## Stack
| Módulo | Tecnología |
|---|---|
| `backend/` | Spring Boot 2.7.18 · Java 8 (`--release 8`) · Spring for GraphQL · Spring Security JWT |
| `frontend/` | React 18 · TypeScript 5 · Apollo Client 3 · graphql-codegen |
| Contrato | `backend/src/main/resources/graphql/schema.graphqls` |

## Requisitos
- JDK 17 o 21 (compila a Java 8) y Maven 3.9+
- Node.js 20+ y npm

## Arranque
```bash
# backend → http://localhost:8080/graphiql
cd backend && mvn spring-boot:run

# frontend → http://localhost:5173
cd frontend && npm install && npm run dev
```

Token de desarrollo: `GET http://localhost:8080/dev/token?user=c1&role=USER`
(clientes semilla: `c1` Ana, `c2` Luis).

## Material de la Sesión 1
- `AGENTS.md` (raíz, `backend/`, `frontend/`) — instrucciones para agentes
- `.cursor/rules/` — rules de proyecto (always, por globs y por descripción)
- `.cursor/agents/security-reviewer.md` — subagente de revisión de solo lectura
- `.cursorignore` / `.cursorindexingignore` — control de acceso e indexación
- `prompts/sesion-1.md` — prompts de las demos
- `docs/laboratorio-1.md` — Laboratorio 1 (11:00–12:00, trabajo autónomo por equipos)
- `docs/lab1-plantilla.md` — plantilla de entrega del laboratorio
- `docs/ejercicios-sesion-1.md` — ejercicios propuestos
