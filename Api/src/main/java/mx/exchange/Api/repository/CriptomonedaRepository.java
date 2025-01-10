package mx.exchange.Api.repository;

import mx.exchange.Api.domain.Criptomoneda;
import mx.exchange.Api.dto.CrearCriptomonedaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CriptomonedaRepository extends JpaRepository<Criptomoneda, Long> {
    CrearCriptomonedaDTO findByTicker(String ticker);
}
