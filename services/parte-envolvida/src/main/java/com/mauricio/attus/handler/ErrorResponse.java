package com.mauricio.attus.handler;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
) {
}
