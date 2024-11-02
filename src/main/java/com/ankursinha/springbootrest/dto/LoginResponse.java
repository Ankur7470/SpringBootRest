package com.ankursinha.springbootrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
        @JsonProperty("message") String message
) {}
