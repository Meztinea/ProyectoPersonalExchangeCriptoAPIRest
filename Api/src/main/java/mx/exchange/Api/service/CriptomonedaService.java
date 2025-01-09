package mx.exchange.Api.service;

import mx.exchange.Api.domain.Criptomoneda;
import mx.exchange.Api.dto.CrearCriptomonedaDTO;
import mx.exchange.Api.repository.CriptomonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriptomonedaService {

    @Autowired
    private final CriptomonedaRepository criptomonedaRepository;

    public CriptomonedaService(CriptomonedaRepository criptomonedaRepository){
        this.criptomonedaRepository = criptomonedaRepository;
    }

    public CrearCriptomonedaDTO crearCriptomoneda(CrearCriptomonedaDTO criptomonedaDTO){

        return new CrearCriptomonedaDTO(criptomonedaRepository.save(new Criptomoneda(criptomonedaDTO)));
    }
}
