# Laboratorio 1 · Cursor sobre un codebase real

Curso *Cursor para Desarrolladores FullStack de Spring y React* · Sesión 1 · Joan Cifo

**Horario:** 11:00 – 12:00, trabajo autónomo por equipos. Puesta en común a las 12:00 en la sala principal.

Joan Cifo no estará conectado durante el laboratorio. Todo lo que necesitáis está en este documento.

---

## Antes de empezar (11:00)

1. **Equipos de 3** en la sala de Zoom asignada. Las salas se cierran solas a las 12:00. Si se cierran antes, seguid en la sala principal con el micro silenciado y comunicaos por el chat.
2. **Roles.** Rotadlos en cada parte:
   - **Piloto:** comparte pantalla y teclea.
   - **Navegante:** lee este documento en voz alta y controla el tiempo.
   - **Verificador:** abre cada `ruta:línea` que cite el agente y desconfía de todo.
3. **Repositorio:** trabajad en el portátil del piloto. Cread la rama del equipo con `git checkout -b s1/<equipo>`.
4. **Entregable:** copiad `docs/lab1-plantilla.md` como `docs/lab1-<equipo>.md` y rellenadlo durante el laboratorio.
5. **Modo de Cursor:** Ask por defecto. Usad Agent solo en la Parte B para crear la rule y editar `AGENTS.md`.

> **Atasco de más de 5 minutos:** anotad la duda en la sección *Dudas* del entregable y pasad al siguiente paso. Las resolvemos a las 12:00.

---

## Parte A · Onboarding con evidencias (11:00 – 11:25)

### A1 · Preparar (5 min)

1. En Cursor: *File → Open Workspace from File…* → `eventhub.code-workspace`.
2. Ajustad en ese fichero las rutas de JDK a vuestra máquina:
   - `java.jdt.ls.java.home` → JDK 17 o superior.
   - `java.configuration.runtimes` → JDK 8 si lo tenéis. Si no lo tenéis, dejad solo el JDK moderno: el proyecto compila igualmente con `--release 8`.
3. En *Cursor Settings → Indexing & Docs*, comprobad que el índice del proyecto está completo.

### A2 · Onboarding (10 min)

1. Cambiad a modo **Ask** con `Shift + Tab` o con el selector de modo.
2. Copiad el prompt **P1** de `prompts/sesion-1.md` y lanzadlo.
3. Leed la respuesta completa. Fijaos en el panel de herramientas: qué archivos ha buscado y leído.

### A3 · Verificar (5 min)

Rol protagonista: **verificador**.

1. Elegid **3 afirmaciones** de la respuesta que citen `ruta:línea`.
2. Abrid cada referencia y marcadla como **✔ correcta** o **✘ falsa o imprecisa**.
3. Anotadlo en la tabla del entregable.

### A4 · Contexto mínimo (5 min)

Bug: *«Con la mutation `cancelOrder` un cliente puede cancelar pedidos de otro cliente».*

1. Elegid **solo 3 archivos** de entre estos y adjuntadlos con `@`:
   `schema.graphqls` · `OrderGraphqlController.java` · `OrderService.java` · `CurrentUser.java` · `MyOrdersPage.tsx` · `package.json` · `InMemoryStore.java`
2. Lanzad el prompt **P2** de `prompts/sesion-1.md`, ajustando los archivos adjuntos.
3. En un **chat nuevo**, repetid la pregunta adjuntando la carpeta `backend/` entera.
4. Comparad las dos respuestas: precisión, longitud y consumo de contexto (indicador del chat).

**Checkpoint A:** tabla de 3 afirmaciones, las 3 piezas elegidas con su justificación y la comparación.
**Reto ★★:** preguntad al agente *«¿Qué parte de tu respuesta tiene menos evidencia?»* y anotad lo que contesta.

---

## Parte B · Rules que se aplican (11:25 – 11:40)

### B1 · Crear la rule (5 min)

Cread `.cursor/rules/frontend-react.mdc`. Podéis partir de este esqueleto:

```markdown
---
description: Convenciones de componentes React del frontend de EventHub
globs: frontend/src/**/*.tsx
alwaysApply: false
---

# Frontend React

1. Usa los tipos de `src/generated/graphql.ts`; no añadas tipos a `src/types/models.ts`.
2. Los importes llegan como String decimal: formatéalos con `Intl.NumberFormat('es-ES', { style: 'currency', currency: 'EUR' })`.
3. Prohibido `any`.
```

Podéis cambiar las reglas, pero tienen que ser **verificables**.

### B2 · Comprobar que se aplica (4 min)

1. Abrid un **chat nuevo** en modo Ask y adjuntad `@EventsPage.tsx`.
2. Preguntad: *«¿Cómo mostrarías el precio de cada tipo de entrada?»*.
3. Comprobad dos cosas: que la rule aparece como **aplicada** en el chat y que la respuesta **la cumple**.

### B3 · Romperla a propósito (3 min)

1. Cambiad `globs` a `**/*.jsx` y repetid B2 en un chat nuevo.
2. ¿Se aplica la rule? Anotad lo que observáis y por qué creéis que ocurre.
3. **Restaurad** el globs original.

### B4 · Memoria de equipo (3 min)

Añadid al final de `AGENTS.md` (raíz) este apartado, con al menos una entrada real de lo que visteis en la Parte A:

```markdown
## Errores frecuentes del agente en este repositorio
- <qué hizo mal el agente> → <qué debe hacer en su lugar>
```

**Checkpoint B:** contenido de la rule, evidencia de que se aplicó (texto o captura del chat), resultado de B3 y entrada de AGENTS.md.
**Reto ★★:** quitad los `globs`, dejad solo la `description` y repetid B2. ¿Qué cambia?

---

## Parte C · Detective de contrato (11:40 – 11:55)

> No arregléis nada en esta parte. Solo investigad y documentad.

### C1 · Observar (4 min)

```bash
cd frontend
npm install
npm run typecheck
npm run codegen
```

Anotad qué ocurre con cada comando. Spoiler: algo no cuadra.

### C2 · Investigar (7 min)

En modo Ask, lanzad el prompt **P5** de `prompts/sesion-1.md`: una tabla de verificación de todas las operaciones de `frontend/src/graphql/operations.ts` contra `schema.graphqls`.

### C3 · Clasificar (4 min)

Para cada discrepancia, anotad:

- `ruta:línea`
- dónde falla: **compilación**, **petición al servidor** o **runtime en el navegador**

**Checkpoint C:** lista de discrepancias con evidencia, dónde falla cada una e hipótesis de por qué `typecheck` pasa y `codegen` no.
**Reto ★★:** ¿alguna discrepancia que `codegen` **no** detecta? ¿Por qué?

---

## Entrega (11:55 – 12:00)

```bash
git add docs/lab1-<equipo>.md .cursor/rules/frontend-react.mdc AGENTS.md
git commit -m "lab1: <equipo>"
git push -u origin s1/<equipo>
```

Elegid **portavoz** para la puesta en común de las 12:00.

## Puntuación (ranking de equipos)

| Concepto | Puntos |
|---|---|
| Checkpoint completo (A, B o C) | +2 cada uno |
| Reto ★★ resuelto | +1 cada uno |
| Discrepancia de contrato correcta con evidencia | +1 |
| Discrepancia que ningún otro equipo encontró | +2 |

## Problemas frecuentes

| Síntoma | Qué hacer |
|---|---|
| `npm run codegen` falla con *Cannot query field "venueName"* | **Es esperado.** Es el objeto de la Parte C. |
| Maven tarda mucho la primera vez | Es la descarga de dependencias. En este laboratorio no hace falta compilar el backend. |
| El índice de Cursor no termina | Revisad que `node_modules/` y `target/` estén en `.cursorindexingignore`. Seguid trabajando con `@` explícitos. |
| No veis si la rule se aplicó | Abrid un **chat nuevo**: las rules se evalúan al iniciar la conversación y al adjuntar archivos. |
| El agente quiere modificar archivos | Estáis en modo Agent: cambiad a **Ask** (`Shift + Tab`). |
