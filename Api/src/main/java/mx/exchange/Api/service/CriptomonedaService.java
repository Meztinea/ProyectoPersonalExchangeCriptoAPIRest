package mx.exchange.Api.service;

import jakarta.persistence.EntityNotFoundException;
import mx.exchange.Api.domain.Criptomoneda;
import mx.exchange.Api.dto.CrearCriptomonedaDTO;
import mx.exchange.Api.repository.CriptomonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CriptomonedaService {

    @Autowired
    private final CriptomonedaRepository criptomonedaRepository;

    public CriptomonedaService(CriptomonedaRepository criptomonedaRepository){
        this.criptomonedaRepository = criptomonedaRepository;
    }

    public CrearCriptomonedaDTO crearCriptomoneda(CrearCriptomonedaDTO criptomonedaDTO){
        if (criptomonedaRepository.existsByTicker(criptomonedaDTO.ticker()))
            throw new DuplicateKeyException("El ticker: " + criptomonedaDTO.ticker() + " ya está registrado.");
        return new CrearCriptomonedaDTO(criptomonedaRepository.save(new Criptomoneda(criptomonedaDTO)));
    }

    public CrearCriptomonedaDTO consultarCriptomoneda(String ticker){
        return criptomonedaRepository.findByTicker(ticker).map(criptomoneda ->
                new CrearCriptomonedaDTO(criptomoneda.getTicker(), criptomoneda.getPrecioActual()))
                .orElseThrow(() -> new EntityNotFoundException("Criptomoneda no encontrada"));
    }
}
