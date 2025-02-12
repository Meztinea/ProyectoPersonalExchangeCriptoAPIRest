package mx.exchange.Api.repository;

import mx.exchange.Api.domain.Transaccion;
import org.springframework.data.repository.CrudRepository;

public interface TransaccionRepository extends CrudRepository<Transaccion, Long> {
}
