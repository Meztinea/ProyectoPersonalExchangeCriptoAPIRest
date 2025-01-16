package mx.exchange.Api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

public record ActualizarPrecioCriptomonedaDTO(
        @NotNull
        @JsonProperty("payload")
        Payload payload
) {
    public record Payload(
            @NotNull
            @JsonProperty("last")
            Double ultimoPrecio){
    }
}
