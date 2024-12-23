package mx.exchange.Api.controller;

import mx.exchange.Api.dto.CrearUsuarioDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    public void registrarUsuario(@RequestBody CrearUsuarioDTO usuario){
        System.out.println(usuario);
    }
}
