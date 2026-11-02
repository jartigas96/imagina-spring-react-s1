# AGENTS.md — backend (Spring Boot 2.7 · Java 8)

- El bytecode debe ser **Java 8** (`maven.compiler.release=8`). Prohibido: `var`, records, `List.of`/`Map.of`,
  text blocks, `switch` con flechas, `Optional.isEmpty()`, `String.isBlank()`/`strip()`/`repeat()`.
- Spring Boot 2.7 usa `javax.*` (no `jakarta.*`) y Spring Security 5.x (`antMatchers`, no `requestMatchers`).
- Capas: `graphql/` y `rest/` (entrada) → `service/` (lógica) → `store/` (datos). Los controladores no
  acceden al store.
- Autorización: en el **recurso** (método del controlador o servicio con `@PreAuthorize`), nunca solo en la URL.
- Tests: JUnit 5 + `HttpGraphQlTester` para el contrato GraphQL. Ejecuta `mvn test` tras cada cambio.
