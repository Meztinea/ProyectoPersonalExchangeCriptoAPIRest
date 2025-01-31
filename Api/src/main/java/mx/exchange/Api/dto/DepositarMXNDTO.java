package mx.exchange.Api.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositarMXNDTO(@NotNull BigDecimal monto) {
}
