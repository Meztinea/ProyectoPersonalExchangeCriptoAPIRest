package mx.exchange.Api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mx.exchange.Api.domain.Tipo;
import mx.exchange.Api.domain.Transaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MostrarTransaccionDTO(
        @NotBlank
        String criptomoneda,
        @NotBlank
        Tipo tipo,
        @NotNull
        LocalDateTime fechaTransaccion,
        @NotNull
        BigDecimal montoMXN,
        @NotNull
        BigDecimal cantidadCriptomoneda
) {
    public MostrarTransaccionDTO (Transaccion transaccion) {
        this(transaccion.getCriptomoneda().getTicker(), transaccion.getTipo(),
                transaccion.getFechaTransaccion(), transaccion.getMontoMXN(),
                transaccion.getCantidadCriptomoneda());
    };
}
