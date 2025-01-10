package mx.exchange.Api.controller;

import jakarta.validation.Valid;
import mx.exchange.Api.dto.CrearCriptomonedaDTO;
import mx.exchange.Api.service.CriptomonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/criptomonedas")
public class CriptomonedaController {

    @Autowired
    private final CriptomonedaService criptomonedaService;

    public CriptomonedaController(CriptomonedaService criptomonedaService){
        this.criptomonedaService = criptomonedaService;
    }

    @PostMapping
    public ResponseEntity<CrearCriptomonedaDTO> crearCriptomoneda(@RequestBody @Valid CrearCriptomonedaDTO criptomonedaDTO,
                                                                  UriComponentsBuilder uriComponentsBuilder){
        CrearCriptomonedaDTO nuevaCriptomoneda = criptomonedaService.crearCriptomoneda(criptomonedaDTO);
        URI url = uriComponentsBuilder.path("/criptomonedas/{ticker}")
                .buildAndExpand(criptomonedaDTO.ticker()).toUri();
        return ResponseEntity.created(url).body(nuevaCriptomoneda);
    }
}
