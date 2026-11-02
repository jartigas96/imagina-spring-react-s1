# Ejercicios — Sesión 1

Curso *Cursor para Desarrolladores FullStack de Spring y React* · Joan Cifo

Trabaja en una rama propia: `git checkout -b s1/<tu-nombre>` (el Laboratorio 1 se entrega en la rama del equipo, `s1/<equipo>`).
Cada ejercicio indica el entregable. Los niveles ★ son obligatorios; ★★ y ★★★ son retos.

---

## Ejercicio 1 · Termina el Laboratorio 1 (★)
Si a tu equipo le quedó alguna parte o algún reto ★★ del laboratorio (`docs/laboratorio-1.md`), complétalo.

**Entregable:** `docs/lab1-<equipo>.md` con los tres checkpoints completos, en la rama `s1/<equipo>`.

## Ejercicio 2 · Una rule que realmente se aplica (★)
1. Replica en tu rama personal la rule `.cursor/rules/frontend-react.mdc` de la Parte B.
2. Añade una **4.ª regla verificable** (por ejemplo, sobre accesibilidad o sobre el manejo de `loading`/`error` de Apollo).
3. Pide un cambio en `MyOrdersPage.tsx` y comprueba en el chat que la rule aparece como aplicada y que la respuesta cumple la nueva regla.

**Entregable:** la rule + evidencia (texto o captura del chat) de que se aplica.

## Ejercicio 3 · El contrato manda (★)
1. Ejecuta `cd frontend && npm run codegen`. ¿Por qué falla?
2. Usa el prompt **P5** para obtener la tabla de verificación del contrato.
3. Corrige `operations.ts` para usar `venue { name }`.
4. Sustituye los tipos de `src/types/models.ts` por los generados en `src/generated/graphql.ts`
   en `EventsPage.tsx` y `MyOrdersPage.tsx`.
5. `npm run codegen && npm run typecheck` debe terminar sin errores.

**Entregable:** commit con el arreglo y lista de **todas** las discrepancias encontradas (hay más de una).

## Ejercicio 4 · Caza de vulnerabilidades (★)
Con el subagente `security-reviewer` (prompt **P7**) y tu propio criterio, localiza el máximo número de
problemas de seguridad sembrados en EventHub (hay al menos 10).

**Entregable:** `docs/auditoria-s1.md` con la tabla
`Severidad | Categoría | Evidencia ruta:línea | Impacto | Propuesta`.

## Ejercicio 5 · Arregla uno con método (★★)
Elige **un** hallazgo Alta o Crítica. Aplica el prompt **P8** (análisis previo), valida el plan tú mismo
y después implementa con Agent. Añade un test con caso permitido y caso denegado
(`spring-security-test` → `@WithMockUser` o JWT de prueba).

**Entregable:** rama con el arreglo + test en verde (`mvn test`).

## Ejercicio 6 · Dependencias: ¿vulnerable o explotable? (★★★)
1. `mvn dependency:tree -Dincludes=org.apache.commons:commons-text` y `npm ls --omit=dev`.
2. Investiga la versión de `commons-text` declarada. ¿Tiene avisos de seguridad conocidos?
3. Con Cursor, determina si el código usa la funcionalidad afectada y si llega **input del usuario**.
4. Propón actuación: actualizar, sustituir, eliminar o mitigar. Justifícala.

**Entregable:** ficha breve (máx. 1 página) con versión, origen (directa/transitiva), uso real,
exposición y actuación recomendada.
