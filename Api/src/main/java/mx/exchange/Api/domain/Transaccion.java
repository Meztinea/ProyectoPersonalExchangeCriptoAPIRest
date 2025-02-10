package mx.exchange.Api.domain;

import jakarta.persistence.*;
import lombok.*;
import mx.exchange.Api.dto.DepositarMXNDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transacciones")
@Entity(name = "Transaccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long id;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(targetEntity = Criptomoneda.class)
    @JoinColumn(name = "id_criptomoneda")
    private Criptomoneda criptomoneda;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "fecha_transaccion")
    private LocalDateTime fechaTransaccion;

    @Column(name = "monto_MXN")
    private BigDecimal montoMXN;

    @Column(name = "cantidad_criptomoneda")
    private BigDecimal cantidadCriptomoneda;

    public Transaccion(Usuario usuario, Criptomoneda criptomoneda, Tipo tipo, DepositarMXNDTO depositoDTO){
        this.usuario = usuario;
        this.criptomoneda = criptomoneda;
        this.tipo = tipo;
        this.fechaTransaccion = LocalDateTime.now();
        this.montoMXN = depositoDTO.monto();
        this.cantidadCriptomoneda = depositoDTO.monto();
    }

}
