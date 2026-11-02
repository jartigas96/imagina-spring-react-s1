# Prompts de la Sesión 1 — Joan Cifo

Prompts usados en las demos en directo. Cópialos tal cual en Cursor y compara tu resultado con el de clase.

---

## P1 · Onboarding de un codebase desconocido (modo Ask)
```
Actúa como desarrollador senior que se incorpora hoy a este repositorio.
Sin modificar nada, explícame:
1. Módulos y responsabilidad de cada uno (una línea por módulo).
2. Flujo de una compra: desde el botón del frontend hasta el store del backend,
   citando archivo y método en cada salto.
3. Versiones de Java, Spring Boot, React y cómo se ejecutan los tests.
4. Tres riesgos técnicos que veas, con evidencia ruta:línea.
Si algo no lo puedes comprobar en el código, márcalo como "no verificado".
```

## P2 · Contexto explícito y mínimo
```
@OrderService.java @OrderGraphqlController.java @schema.graphqls
La mutation cancelOrder devuelve el pedido aunque no pertenezca al usuario.
Sin tocar código: explica por qué ocurre y qué archivos cambiarías.
```

## P3 · Plan antes de implementar (modo Plan)
```
Quiero añadir el campo `refundReason: String` al tipo Order (solo visible en backoffice).
Prepara un plan: cambios en schema, dominio, resolvers, frontend y tests.
Indica si el cambio es compatible o breaking y qué validarías en cada paso.
No escribas código hasta que apruebe el plan.
```

## P4 · Trazabilidad de un campo GraphQL
```
@schema.graphqls
Traza el campo TicketType.price de extremo a extremo:
schema → resolver Java (clase y método) → operación GraphQL del frontend → tipo TypeScript
→ componente que lo pinta. Señala cualquier discrepancia de tipos entre capas.
```

## P5 · Validar operaciones contra el contrato real
```
Revisa todas las operaciones de @frontend/src/graphql/operations.ts contra @schema.graphqls.
Para cada campo usado: existe (sí/no), tipo en el schema, tipo que asume el código consumidor.
No propongas soluciones todavía: solo la tabla de verificación.
```

## P6 · ¿Es breaking?
```
Propuesta de cambio en el schema: renombrar `Event.date` a `Event.startsAt` y pasar
`Customer.phone` de opcional a obligatorio.
Clasifica cada cambio como compatible o breaking, explica el motivo y propone
una estrategia de migración sin romper clientes (deprecación, convivencia, plazos).
```

## P7 · Auditoría de seguridad guiada (subagente de solo lectura)
```
/security-reviewer
Audita la autorización de todas las queries y mutations GraphQL y de los endpoints REST.
Para cada operación: quién debería poder ejecutarla, qué control existe hoy (con evidencia)
y si hay control de propiedad del recurso.
```

## P8 · Análisis antes de tocar un mecanismo crítico
```
Necesito proteger refundOrder para que solo lo ejecute ROLE_ADMIN.
Antes de escribir código aplica @seguridad.mdc: superficie afectada, estado actual con evidencias,
riesgos, plan y tests (caso permitido y caso denegado).
```
