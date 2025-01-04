package mx.exchange.Api.service;

import mx.exchange.Api.domain.Usuario;
import mx.exchange.Api.dto.CrearUsuarioDTO;
import mx.exchange.Api.dto.MostrarUsuarioDTO;
import mx.exchange.Api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public MostrarUsuarioDTO crearUsuario(CrearUsuarioDTO usuario){
        return new MostrarUsuarioDTO(usuarioRepository.save(new Usuario(usuario)));
    }

    public MostrarUsuarioDTO consultarUsuarioPorId(Long id){
        return new MostrarUsuarioDTO(usuarioRepository.getReferenceById(id));
    }
}
