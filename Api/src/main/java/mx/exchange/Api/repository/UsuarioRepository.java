package mx.exchange.Api.repository;

import mx.exchange.Api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreoElectronico(String correoElectronico);
}
