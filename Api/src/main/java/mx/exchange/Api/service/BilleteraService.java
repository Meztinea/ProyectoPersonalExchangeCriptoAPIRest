package mx.exchange.Api.service;

import mx.exchange.Api.dto.DepositarMXNDTO;
import mx.exchange.Api.repository.BilleteraRepository;
import mx.exchange.Api.repository.CriptomonedaRepository;
import mx.exchange.Api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BilleteraService {

    @Autowired
    private final BilleteraRepository billeteraRepository;
    private final UsuarioRepository usuarioRepository;
    private final CriptomonedaRepository criptomonedaRepository;

    public BilleteraService(BilleteraRepository billeteraRepository, UsuarioRepository usuarioRepository,
                            CriptomonedaRepository criptomonedaRepository){
        this.billeteraRepository = billeteraRepository;
        this.usuarioRepository = usuarioRepository;
        this.criptomonedaRepository = criptomonedaRepository;
    }

    public void depositarMXN(Long idUsuario, DepositarMXNDTO depositoDTO){

    }

}
