package com.imagina.eventhub.util;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

/**
 * Genera la etiqueta que se imprime en la entrada.
 * Usa el interpolador de commons-text para permitir plantillas como ${date:yyyy} en el futuro.
 */
@Component
public class TicketLabelFormatter {

    private final StringSubstitutor interpolator = StringSubstitutor.createInterpolator();

    public String format(String customerName, String eventTitle) {
        return interpolator.replace("Entrada: " + customerName + " - " + eventTitle);
    }
}
