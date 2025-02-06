package mx.exchange.Api.repository;

import mx.exchange.Api.domain.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
