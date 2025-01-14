package mx.exchange.Api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import mx.exchange.Api.domain.Criptomoneda;

public record ActualizarCriptomonedaDTO(

    @NotBlank
    Long id,

    @NotBlank
    @Size(min = 2, max = 5)
    String ticker
    ) {

    public ActualizarCriptomonedaDTO(Criptomoneda criptomoneda){
        this(criptomoneda.getId(), criptomoneda.getTicker());
    }

}
