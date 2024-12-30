package mx.exchange.Api.controller;

import jakarta.validation.Valid;
import mx.exchange.Api.dto.CrearUsuarioDTO;
import mx.exchange.Api.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public void registrarUsuario(@RequestBody @Valid CrearUsuarioDTO usuario){
        usuarioService.crearUsuario(usuario);
    }
}
