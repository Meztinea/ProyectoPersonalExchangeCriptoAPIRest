package mx.exchange.Api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mx.exchange.Api.dto.ActualizarCriptomonedaDTO;
import mx.exchange.Api.dto.CrearCriptomonedaDTO;
import mx.exchange.Api.service.CriptomonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @Transactional
    public ResponseEntity<CrearCriptomonedaDTO> crearCriptomoneda(@RequestBody @Valid CrearCriptomonedaDTO criptomonedaDTO,
                                                                  UriComponentsBuilder uriComponentsBuilder){
        CrearCriptomonedaDTO nuevaCriptomoneda = criptomonedaService.crearCriptomoneda(criptomonedaDTO);
        URI url = uriComponentsBuilder.path("/criptomonedas/{ticker}")
                .buildAndExpand(criptomonedaDTO.ticker()).toUri();
        return ResponseEntity.created(url).body(nuevaCriptomoneda);
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<CrearCriptomonedaDTO> consultarCriptomoneda(@PathVariable String ticker){
        CrearCriptomonedaDTO criptomoneda = criptomonedaService.consultarCriptomoneda(ticker);
        return ResponseEntity.ok().body(criptomoneda);
    }

    @GetMapping
    public ResponseEntity <Page<CrearCriptomonedaDTO>> consultarCriptomonedas(Pageable paginacion){
        Page<CrearCriptomonedaDTO> listaCriptomonedas = criptomonedaService.consultarCriptomonedas(paginacion);
        return ResponseEntity.ok().body(listaCriptomonedas);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ActualizarCriptomonedaDTO> actualizarCriptomoneda(@RequestBody ActualizarCriptomonedaDTO criptomonedaDTO){
        ActualizarCriptomonedaDTO criptomoneda = criptomonedaService.actualizarCriptomoneda(criptomonedaDTO);
        return ResponseEntity.ok().body(criptomoneda);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCriptomoneda(@PathVariable Long id){
        criptomonedaService.eliminarCriptomoneda(id);
        return ResponseEntity.noContent().build();
    }
}
