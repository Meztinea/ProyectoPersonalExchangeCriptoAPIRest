package mx.exchange.Api.repository;

import mx.exchange.Api.domain.Criptomoneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CriptomonedaRepository extends JpaRepository<Criptomoneda, Long> {
    Optional<Criptomoneda> findByTicker(String ticker);
}
