package mx.exchange.Api.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import mx.exchange.Api.domain.Criptomoneda;
import mx.exchange.Api.dto.ActualizarCriptomonedaDTO;
import mx.exchange.Api.dto.ActualizarPrecioCriptomonedaDTO;
import mx.exchange.Api.dto.CrearCriptomonedaDTO;
import mx.exchange.Api.repository.CriptomonedaRepository;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class CriptomonedaService {

    private static List<Criptomoneda> listaCriptomonedas = new ArrayList<>();

    @Autowired
    private final CriptomonedaRepository criptomonedaRepository;
    private final RestTemplate restTemplate;

    public CriptomonedaService(CriptomonedaRepository criptomonedaRepository,
                               RestTemplate restTemplate) {
        this.criptomonedaRepository = criptomonedaRepository;
        this.restTemplate = restTemplate;
    }

    public CrearCriptomonedaDTO crearCriptomoneda(CrearCriptomonedaDTO criptomonedaDTO) {
        if (criptomonedaRepository.existsByTicker(criptomonedaDTO.ticker()))
            throw new DuplicateKeyException("El ticker: " + criptomonedaDTO.ticker() + " ya está registrado.");
        return new CrearCriptomonedaDTO(criptomonedaRepository.save(new Criptomoneda(criptomonedaDTO)));
    }

    public CrearCriptomonedaDTO consultarCriptomoneda(String ticker) {
        return criptomonedaRepository.findByTicker(ticker).map(criptomoneda ->
                        new CrearCriptomonedaDTO(criptomoneda.getTicker(), criptomoneda.getPrecioActual()))
                .orElseThrow(() -> new EntityNotFoundException("Criptomoneda no encontrada"));
    }

    public Page<CrearCriptomonedaDTO> consultarCriptomonedas(Pageable paginacion){
        return criptomonedaRepository.findAll(paginacion).map(CrearCriptomonedaDTO::new);
    }

    public ActualizarCriptomonedaDTO actualizarCriptomoneda(ActualizarCriptomonedaDTO criptomonedaDTO) {
        if (!criptomonedaRepository.existsById(criptomonedaDTO.id()))
            throw new EntityNotFoundException("El ticker con id: " + criptomonedaDTO.id() +
                    " no se encuentra registrado y no puede ser actualizado.");
        Criptomoneda criptomoneda = criptomonedaRepository.getReferenceById(criptomonedaDTO.id());
        criptomoneda.actualizarTicker(criptomonedaDTO);
        return new ActualizarCriptomonedaDTO(criptomoneda);
    }

    @Async
    @Scheduled(fixedDelay = 300000)
    @Transactional
    public void actualizarPrecioCriptomonedas(){

        try {
            // Si se agregó una nueva criptomoneda a la base de datos, se actualiza la lista con todas las criptos
            if(listaCriptomonedas.size() != criptomonedaRepository.count())
                listaCriptomonedas = criptomonedaRepository.findAll();


            for (Criptomoneda c : listaCriptomonedas){

                if (c.getTicker().equalsIgnoreCase("MXN"))
                    continue;

                String url = "https://api.bitso.com/api/v3/ticker?book=" + c.getTicker().toLowerCase(Locale.ENGLISH) + "_mxn";

                // Recibe el precio de la criptomoneda real desde la API REST del Exchange Bitso
                ActualizarPrecioCriptomonedaDTO criptomonedaDTO = restTemplate.getForObject(url, ActualizarPrecioCriptomonedaDTO.class);

                // Actualiza el precio de la criptomoneda en la List y en la DB
                Criptomoneda criptomoneda = criptomonedaRepository.getReferenceById(c.getId());
                criptomoneda.actualizarPrecio(criptomonedaDTO);
                c = criptomoneda;
            }
        } catch (LazyInitializationException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void eliminarCriptomoneda(Long id) {
        criptomonedaRepository.deleteById(id);
    }
}