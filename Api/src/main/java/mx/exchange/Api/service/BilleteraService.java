package mx.exchange.Api.service;

import jakarta.transaction.Transactional;
import mx.exchange.Api.domain.*;
import mx.exchange.Api.dto.DepositarMXNDTO;
import mx.exchange.Api.repository.BilleteraRepository;
import mx.exchange.Api.repository.CriptomonedaRepository;
import mx.exchange.Api.repository.TransaccionRepository;
import mx.exchange.Api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class BilleteraService {

    @Autowired
    private final BilleteraRepository billeteraRepository;
    private final UsuarioRepository usuarioRepository;
    private final CriptomonedaRepository criptomonedaRepository;
    private final TransaccionRepository transaccionRepository;

    public BilleteraService(BilleteraRepository billeteraRepository, UsuarioRepository usuarioRepository,
                            CriptomonedaRepository criptomonedaRepository, TransaccionRepository transaccionRepository){
        this.billeteraRepository = billeteraRepository;
        this.usuarioRepository = usuarioRepository;
        this.criptomonedaRepository = criptomonedaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    @Transactional
    public void depositarMXN(Long idUsuario, DepositarMXNDTO depositoDTO){

        String ticker = "MXN";
        Criptomoneda criptomoneda = criptomonedaRepository.getReferenceByTicker(ticker);

        // Verificar que el monto para depositar es mayor a 0 - Lanzar excepción
        if (depositoDTO.monto().compareTo(BigDecimal.ZERO) <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El monto para depositar debe ser mayor a 0.");
        }

        // Verificar que el usuario existe
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar si el usuario ya tiene una billetera en MXN
        Billetera billeteraMXN = usuario.getBilleteraList().stream()
                .filter(billetera -> billetera.getCriptomoneda().getTicker().equals(ticker))
                .findFirst()
                .orElseGet(()-> {
                    // Si no tiene billetera le asigna una en "ceros" 0.00
                    return new Billetera(usuario, criptomoneda);
                });

        // Actualizar el saldo en la billetera y Almacenar en la BD
        billeteraMXN.actualizarCantidad(depositoDTO.monto());
        billeteraRepository.save(billeteraMXN);

        // Crear una transaccion y registrar en el historial de transacciones en la BD
        Transaccion transaccion = new Transaccion(usuario, criptomoneda, Tipo.DEPOSITO, depositoDTO);
        transaccionRepository.save(transaccion);
    }

}
