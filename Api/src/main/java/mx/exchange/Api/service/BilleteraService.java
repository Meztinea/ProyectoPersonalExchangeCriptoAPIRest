package mx.exchange.Api.service;

import mx.exchange.Api.domain.Billetera;
import mx.exchange.Api.domain.BilleteraId;
import mx.exchange.Api.domain.Criptomoneda;
import mx.exchange.Api.domain.Usuario;
import mx.exchange.Api.dto.DepositarMXNDTO;
import mx.exchange.Api.repository.BilleteraRepository;
import mx.exchange.Api.repository.CriptomonedaRepository;
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

    public BilleteraService(BilleteraRepository billeteraRepository, UsuarioRepository usuarioRepository,
                            CriptomonedaRepository criptomonedaRepository){
        this.billeteraRepository = billeteraRepository;
        this.usuarioRepository = usuarioRepository;
        this.criptomonedaRepository = criptomonedaRepository;
    }

    public void depositarMXN(Long idUsuario, DepositarMXNDTO depositoDTO){

        String ticker = "MXN";

        // Verificar que el monto para depositar es mayor a 0
        if (depositoDTO.monto().compareTo(BigDecimal.ZERO) <= 0){
            // Lanzar excepción indicando que el monto debe ser mayor a 0.
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

                    Criptomoneda criptomoneda = criptomonedaRepository.getReferenceByTicker(ticker);

                    Billetera billetera = new Billetera();
                    BilleteraId id = new BilleteraId(idUsuario, criptomoneda.getId());
                    billetera.setId(id);
                    billetera.setUsuario(usuario);
                    billetera.setCriptomoneda(criptomoneda);
                    billetera.setCantidad(BigDecimal.ZERO); // Asigna 0 a la billetera nueva
                    billetera.setFechaCreacion(LocalDateTime.now());
                    billetera.setFechaActualizacion(LocalDateTime.now());

                    return billetera;
                });


        // Actualizar el saldo en la billetera
        billeteraMXN.actualizarCantidad(depositoDTO.monto());

        // Almaceno en la BD
        billeteraRepository.save(billeteraMXN);
    }

}
