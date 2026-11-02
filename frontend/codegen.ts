import type { CodegenConfig } from '@graphql-codegen/cli';

/**
 * Genera los tipos TypeScript a partir del CONTRATO REAL del backend.
 * Fuente de verdad: backend/src/main/resources/graphql/schema.graphqls
 * Las operaciones (gql`...`) se extraen de src/graphql/*.ts y se validan contra el schema:
 * si una query pide un campo que no existe, la generación falla.
 */
const config: CodegenConfig = {
  schema: '../backend/src/main/resources/graphql/schema.graphqls',
  documents: ['src/graphql/**/*.ts'],
  generates: {
    'src/generated/graphql.ts': {
      plugins: ['typescript', 'typescript-operations'],
      config: {
        enumsAsTypes: true,
        avoidOptionals: true,
      },
    },
  },
};

export default config;
