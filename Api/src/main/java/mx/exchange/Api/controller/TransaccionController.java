package mx.exchange.Api.controller;

import mx.exchange.Api.dto.MostrarTransaccionDTO;
import mx.exchange.Api.service.TransaccionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    public final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService){
        this.transaccionService = transaccionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<MostrarTransaccionDTO>>  consultarTransacciones(Pageable paginacion, @PathVariable Long id){
        return ResponseEntity.ok(transaccionService.consultarTransacciones(paginacion, id)) ;
    }
}
