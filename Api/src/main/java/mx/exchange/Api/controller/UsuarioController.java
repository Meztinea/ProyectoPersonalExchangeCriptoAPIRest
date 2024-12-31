package mx.exchange.Api.controller;

import jakarta.validation.Valid;
import mx.exchange.Api.domain.Usuario;
import mx.exchange.Api.dto.CrearUsuarioDTO;
import mx.exchange.Api.dto.MostrarUsuarioDTO;
import mx.exchange.Api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<MostrarUsuarioDTO> registrarUsuario(@RequestBody @Valid CrearUsuarioDTO usuario,
                                           UriComponentsBuilder uriComponentsBuilder){
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        MostrarUsuarioDTO mostrarUsuario = new MostrarUsuarioDTO(nuevoUsuario);
        URI url = UriComponentsBuilder.fromPath("/usuarios/consultar/{id}")
                .buildAndExpand(nuevoUsuario.getId()).toUri();
        return ResponseEntity.created(url).body(mostrarUsuario);
    }
}
