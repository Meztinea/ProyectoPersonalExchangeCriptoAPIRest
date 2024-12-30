package mx.exchange.Api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CrearUsuarioDTO(
        @NotBlank(message = "El nombre completo no puede estar vacío.")
        @Size(max = 200)
        String nombre,
        @Pattern(regexp = "\\d{8,15}")
        String telefono,
        @NotBlank
        @Email
        @Size(max = 100)
        String correoElectronico) {
}
