package mx.exchange.Api.controller;

import jakarta.validation.Valid;
import mx.exchange.Api.dto.CrearUsuarioDTO;
import mx.exchange.Api.dto.MostrarUsuarioDTO;
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
        MostrarUsuarioDTO nuevoUsuario = usuarioService.crearUsuario(usuario);
        URI url = uriComponentsBuilder.path("/usuarios/consultar/{id}")
                .buildAndExpand(nuevoUsuario.id()).toUri();
        return ResponseEntity.created(url).body(nuevoUsuario);
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<MostrarUsuarioDTO> consultarUsuario(@PathVariable Long id){
        MostrarUsuarioDTO nuevoUsuario = usuarioService.consultarUsuarioPorId(id);
        return ResponseEntity.ok(nuevoUsuario);
    }
}
