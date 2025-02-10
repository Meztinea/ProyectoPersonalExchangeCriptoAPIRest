package mx.exchange.Api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Table(name = "billetera")
@Entity(name = "Billetera")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Billetera {

    @EmbeddedId
    private BilleteraId id;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idCriptomoneda")
    @JoinColumn(name = "id_criptomoneda")
    private Criptomoneda criptomoneda;

    private BigDecimal cantidad;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;


    public Billetera(Usuario usuario, Criptomoneda criptomoneda){
        this.id = new BilleteraId(usuario.getId(), criptomoneda.getId());
        this.usuario = usuario;
        this.criptomoneda = criptomoneda;
        this.cantidad = BigDecimal.ZERO; // Asigna 0 a la billetera nueva
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void actualizarCantidad(BigDecimal cantidad){
        this.cantidad = this.cantidad.add(cantidad);
        this.fechaActualizacion = LocalDateTime.now();
    }

}
