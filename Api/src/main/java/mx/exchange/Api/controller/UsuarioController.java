package mx.exchange.Api.controller;

import jakarta.validation.Valid;
import mx.exchange.Api.domain.Usuario;
import mx.exchange.Api.dto.CrearUsuarioDTO;
import mx.exchange.Api.dto.MostrarUsuarioDTO;
import mx.exchange.Api.repository.UsuarioRepository;
import mx.exchange.Api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        URI url = uriComponentsBuilder.path("/usuarios/consultar/{id}")
                .buildAndExpand(nuevoUsuario.getId()).toUri();
        return ResponseEntity.created(url).body(mostrarUsuario);
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<MostrarUsuarioDTO> consultarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.consultarUsuarioPorId(id);
        MostrarUsuarioDTO mostrarUsuario = new MostrarUsuarioDTO(usuario);
        return ResponseEntity.ok(mostrarUsuario);
    }
}
