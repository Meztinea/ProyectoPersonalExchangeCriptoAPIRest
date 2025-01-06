package mx.exchange.Api.dto;

import jakarta.validation.constraints.NotNull;
import mx.exchange.Api.domain.Usuario;

public record ActualizarUsuarioDTO(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        String correoElectronico) {
        public ActualizarUsuarioDTO(Usuario usuario) {
                this(usuario.getId(), usuario.getNombre(), usuario.getTelefono(), usuario.getCorreoElectronico());
        }
}
