package mx.exchange.Api.repository;

import mx.exchange.Api.domain.Billetera;
import mx.exchange.Api.domain.BilleteraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilleteraRepository extends JpaRepository<Billetera, BilleteraId> {
}
