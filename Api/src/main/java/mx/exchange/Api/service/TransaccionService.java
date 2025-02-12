package mx.exchange.Api.service;

import jakarta.persistence.EntityNotFoundException;
import mx.exchange.Api.domain.Usuario;
import mx.exchange.Api.dto.MostrarTransaccionDTO;
import mx.exchange.Api.repository.TransaccionRepository;
import mx.exchange.Api.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    public final TransaccionRepository transaccionRepository;
    public final UsuarioRepository usuarioRepository;

    public TransaccionService(TransaccionRepository transaccionRepository,
                              UsuarioRepository usuarioRepository){
        this.transaccionRepository = transaccionRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public Page<MostrarTransaccionDTO> consultarTransacciones(Pageable paginacion, Long id){

        // Verifica que el usuario existe
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con id: " + id + " no encontrado"));

        // Obtiene lista de transacciones pertenecientes al usuario
        List<MostrarTransaccionDTO> transacciones = usuario.getTransaccionList()
                .stream()
                .map(MostrarTransaccionDTO::new)
                .toList();


        // Revisar buenas prácticas
        // Revisar lo que se devuelve al usuario en el controller
        // No usar transactional

        // Configura la paginación con los datos del objeto Pageable y la lista de transacciones
        int inicio = (int)paginacion.getOffset();
        int fin = Math.min(inicio + paginacion.getPageSize(), transacciones.size());

        return new PageImpl<>(transacciones.subList(inicio,fin), paginacion, transacciones.size());
    }



}
