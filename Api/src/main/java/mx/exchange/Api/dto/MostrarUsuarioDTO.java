package mx.exchange.Api.dto;

import mx.exchange.Api.domain.Usuario;

import java.time.LocalDateTime;

public record MostrarUsuarioDTO(Long id, String nombre, String telefono, String correoElectronico,
                                LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {

    public MostrarUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getTelefono(), usuario.getCorreoElectronico(),
                usuario.getFechaCreacion(), usuario.getFechaActualizacion());
    }
}
