package mx.exchange.Api.controller;


import mx.exchange.Api.dto.DepositarMXNDTO;
import mx.exchange.Api.service.BilleteraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billeteras")
public class BilleteraController {

    @Autowired
    private final BilleteraService billeteraService;

    public BilleteraController(BilleteraService billeteraService){
        this.billeteraService = billeteraService;
    }

    @PostMapping("depositar/{idUsuario}")
    public void depositar(@PathVariable Long idUsuario, @RequestBody DepositarMXNDTO depositoDTO){

        billeteraService.depositarMXN(idUsuario, depositoDTO);
    }
}
