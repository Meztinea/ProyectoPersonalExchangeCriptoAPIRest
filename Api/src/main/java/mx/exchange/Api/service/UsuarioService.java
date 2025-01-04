package mx.exchange.Api.service;

import mx.exchange.Api.domain.Usuario;
import mx.exchange.Api.dto.CrearUsuarioDTO;
import mx.exchange.Api.dto.MostrarUsuarioDTO;
import mx.exchange.Api.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    public Page<MostrarUsuarioDTO> consultarUsuarios(Pageable paginacion){
        return usuarioRepository.findAll(paginacion).map(MostrarUsuarioDTO::new);
    }
}
