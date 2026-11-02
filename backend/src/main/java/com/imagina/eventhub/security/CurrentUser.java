package com.imagina.eventhub.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/** Acceso al usuario autenticado. El "sub" del JWT es el id de cliente (c1, c2...). */
public final class CurrentUser {

    private CurrentUser() {
    }

    public static String customerId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new IllegalStateException("No hay usuario autenticado");
        }
        return auth.getName();
    }
}
