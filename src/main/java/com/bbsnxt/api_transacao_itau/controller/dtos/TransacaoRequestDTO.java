package com.bbsnxt.api_transacao_itau.controller.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(

        @NotNull (message = "O valor não pode ser nulo")
        @PositiveOrZero(message = "O valor não pode ser negativo")
        Double valor,

        @NotNull(message = "A data e hora não podem ser nulas")
        @PastOrPresent(message = "A data e hora não podem estar no futuro")
        OffsetDateTime dataHora
) { }
