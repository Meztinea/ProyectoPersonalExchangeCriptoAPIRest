package mx.exchange.Api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import mx.exchange.Api.domain.Criptomoneda;

public record CrearCriptomonedaDTO(

        @NotBlank
        @Size(min = 2, max = 5)
        String ticker,

        @NotNull
        Double precioActual) {

        public CrearCriptomonedaDTO(Criptomoneda criptomoneda) {
                this(criptomoneda.getTicker(), criptomoneda.getPrecioActual());
        }
}
