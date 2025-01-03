package mx.exchange.Api.service;

import mx.exchange.Api.domain.Usuario;
import mx.exchange.Api.dto.CrearUsuarioDTO;
import mx.exchange.Api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(CrearUsuarioDTO usuario){
        return usuarioRepository.save(new Usuario(usuario));
    }

    public Usuario consultarUsuarioPorId(Long id){
        return usuarioRepository.getReferenceById(id);
    }
}
