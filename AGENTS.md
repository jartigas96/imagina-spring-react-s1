# AGENTS.md — EventHub

Instrucciones para cualquier agente (Cursor, CLI o agentes remotos) que trabaje en este repositorio.

## Qué es
EventHub: venta de entradas para eventos. Monorepo con dos módulos:
- `backend/` — Spring Boot 2.7 + **Java 8** + Spring for GraphQL + Spring Security (JWT).
- `frontend/` — React 18 + TypeScript + Apollo Client + graphql-codegen.

Contrato entre ambos: `backend/src/main/resources/graphql/schema.graphqls` (**fuente de verdad**).

## Comandos
| Acción | Comando |
|---|---|
| Tests backend | `cd backend && mvn test` |
| Arrancar backend | `cd backend && mvn spring-boot:run` |
| Typecheck frontend | `cd frontend && npm run typecheck` |
| Generar tipos GraphQL | `cd frontend && npm run codegen` |
| Comprobar contrato | `cd frontend && npm run codegen:check` |

## Forma de trabajar (obligatoria)
1. **Analizar** antes de tocar código: lista los archivos implicados y cita las líneas.
2. **Planificar**: propone el plan y espera confirmación si afecta a más de 3 archivos.
3. **Implementar** en incrementos pequeños.
4. **Validar**: ejecuta el comando de la tabla que corresponda al módulo tocado.
5. **Revisar**: resume archivos modificados, decisiones y riesgos.
6. **Documentar**: si cambia el contrato o un comando, actualiza este fichero o el README.

## Límites
- No inventes campos, queries ni endpoints: compruébalos en el schema o en el código.
- No edites `frontend/src/generated/**` a mano: se regenera con `npm run codegen`.
- No modifiques autenticación, autorización, CORS ni gestión de tokens sin un análisis previo
  aprobado por una persona (ver `.cursor/rules/seguridad.mdc`).
- Nunca escribas secretos, tokens ni credenciales en código, prompts, rules o documentación.

Cada módulo tiene su propio `AGENTS.md` con reglas específicas (el más cercano al archivo manda).
