package com.imagina.eventhub.error;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/** Traduce excepciones de los resolvers a errores GraphQL legibles para el frontend. */
@Component
public class GraphqlErrorResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        Map<String, Object> extensions = new HashMap<String, Object>();
        extensions.put("exception", ex.getClass().getName());
        extensions.put("cause", String.valueOf(ex.getCause()));
        if (ex.getStackTrace().length > 0) {
            extensions.put("at", ex.getStackTrace()[0].toString());
        }
        return GraphqlErrorBuilder.newError(env)
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(ex.getMessage())
                .extensions(extensions)
                .build();
    }
}
